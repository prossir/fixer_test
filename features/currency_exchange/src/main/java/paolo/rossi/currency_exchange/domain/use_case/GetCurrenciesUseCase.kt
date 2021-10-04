package paolo.rossi.currency_exchange.domain.use_case

import androidx.lifecycle.LiveData
import paolo.rossi.currency_exchange.domain.entity.Currency
import paolo.rossi.currency_exchange.domain.repository.CurrencyRepository


class GetCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(filterText: LiveData<String>) : LiveData<List<Currency>> {
        return currencyRepository.getCurrencies(filterText)
    }

}