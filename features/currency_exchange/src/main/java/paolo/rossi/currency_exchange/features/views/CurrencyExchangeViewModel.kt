package paolo.rossi.currency_exchange.features.views

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import paolo.rossi.core.extensions.safeLaunch
import paolo.rossi.core.extensions.withDispatcher
import paolo.rossi.currency_exchange.domain.use_case.GetCurrenciesUseCase
import paolo.rossi.currency_exchange.features.dto.CurrencyModel
import paolo.rossi.currency_exchange.features.mapper.CurrencyExchangeFailureMapper
import paolo.rossi.currency_exchange.features.mapper.CurrencyMapper


class CurrencyExchangeViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val mapper: CurrencyMapper,
    private val failureMapper: CurrencyExchangeFailureMapper
) : ViewModel() {

    val state = MutableLiveData<CurrencyExchangeViewState>().apply {
        this.value = CurrencyExchangeViewState.None
    }

    var isLoading = View.GONE

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

    fun fetchLiveFilterableCurrencies() {
        isLoading = View.VISIBLE
        viewModelScope.safeLaunch(::handleException) {
            _filterableLiveCurrencies = withDispatcher(Dispatchers.IO) { Transformations.map(getCurrenciesUseCase(filterText)) { mapper.map(it) } }
            state.value = CurrencyExchangeViewState.AfterCurrenciesAreLoaded
            isLoading = View.GONE
        }
    }

    private fun handleException(t: Throwable) {
        val failure = failureMapper.map(t)
        isLoading = View.GONE
        state.value = CurrencyExchangeViewState.OnError
    }

}