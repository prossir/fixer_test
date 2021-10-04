package paolo.rossi.currency_exchange.features.views

import androidx.appcompat.widget.SearchView
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import paolo.rossi.core.extensions.safeLaunch
import paolo.rossi.core.extensions.withDispatcher
import paolo.rossi.currency_exchange.domain.use_case.GetCurrenciesUseCase
import paolo.rossi.currency_exchange.domain.use_case.GetExchangeRatesOfCurrencyUseCase
import paolo.rossi.currency_exchange.features.dto.CurrencyModel
import paolo.rossi.currency_exchange.features.dto.ExchangeRateModel
import paolo.rossi.currency_exchange.features.mapper.CurrencyExchangeFailureMapper
import paolo.rossi.currency_exchange.features.mapper.CurrencyMapper
import paolo.rossi.currency_exchange.features.mapper.ExchangeRateMapper
import paolo.rossi.currency_exchange.utils.extensions.roundedTo


class CurrencyExchangeViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getExchangeRatesOfCurrencyUseCase: GetExchangeRatesOfCurrencyUseCase,
    private val currencyMapper: CurrencyMapper,
    private val exchangeRateMapper: ExchangeRateMapper,
    private val failureMapper: CurrencyExchangeFailureMapper
) : ViewModel() {

    val state: LiveData<CurrencyExchangeViewState>
        get() = _state
    private val _state: MutableLiveData<CurrencyExchangeViewState> = MutableLiveData(CurrencyExchangeViewState.None)

    var isLoading : ObservableBoolean = ObservableBoolean(false)
    var isError : ObservableBoolean = ObservableBoolean(false)

    private val filterText : MutableLiveData<String> = MutableLiveData(null)
    val onQueryTextChange = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean = true
        override fun onQueryTextChange(newText: String?): Boolean {
            filterText.value = newText
            return true
        }
    }

    val filterableLiveCurrencies: LiveData<List<CurrencyModel>>
        get() = _filterableLiveCurrencies
    private lateinit var _filterableLiveCurrencies: LiveData<List<CurrencyModel>>

    val liveExchangeRates: LiveData<List<ExchangeRateModel>>
        get() = _liveExchangeRates
    private lateinit var _liveExchangeRates: LiveData<List<ExchangeRateModel>>


    fun fetchLiveFilterableCurrencies() {
        isLoading.set(true)
        isError.set(false)
        viewModelScope.safeLaunch(::handleFilterableCurrenciesException) {
            _filterableLiveCurrencies = withDispatcher(Dispatchers.IO) { Transformations.map(getCurrenciesUseCase(filterText)) { currencyMapper.map(it) } }
            _state.value = CurrencyExchangeViewState.AfterCurrenciesAreLoaded
            isLoading.set(false)
        }
    }

    private fun handleFilterableCurrenciesException(t: Throwable) {
        isLoading.set(false)
        isError.set(true)
        _state.value = failureMapper.map(t).localizedMessage?.let { CurrencyExchangeViewState.OnError(it) }
    }

    var selectedBaseCurrency = ""
    var quantityToExchangeAsText: String
        get() = "${quantityToExchange.value}"
        set(value) { value.toDoubleOrNull()?.let { quantityToExchange.value = it } }
    private val quantityToExchange : MutableLiveData<Double> = MutableLiveData(0.0)

    fun fetchLiveExchangeRates(baseCurrency: String) {
        isLoading.set(true)
        selectedBaseCurrency = baseCurrency
        viewModelScope.safeLaunch(::handleExchangeRatesException) {
            val exchangeRates = withDispatcher(Dispatchers.IO) { Transformations.map(getExchangeRatesOfCurrencyUseCase(baseCurrency)) { exchangeRateMapper.map(it) } }
            _liveExchangeRates = Transformations.switchMap(quantityToExchange) { currentQuantity ->
                exchangeRates.value?.let {
                    for (value in it) {
                        value.baseCurrencyEquivalent.value?.set((currentQuantity * value.exchangeRate).roundedTo(2))
                    }
                }
                return@switchMap exchangeRates
            }
            _state.value = CurrencyExchangeViewState.AfterExchangeRatesAreLoaded
            isLoading.set(false)
        }
    }

    private fun handleExchangeRatesException(t: Throwable) {
        isLoading.set(false)
        _state.value = failureMapper.map(t).localizedMessage?.let { CurrencyExchangeViewState.OnError(it) } ?: CurrencyExchangeViewState.OnError("Unknown error")
    }

}