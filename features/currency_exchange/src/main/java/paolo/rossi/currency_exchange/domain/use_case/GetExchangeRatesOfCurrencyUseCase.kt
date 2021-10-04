package paolo.rossi.currency_exchange.domain.use_case

import androidx.lifecycle.LiveData
import paolo.rossi.currency_exchange.domain.entity.ExchangeRate
import paolo.rossi.currency_exchange.domain.repository.CurrencyRepository


class GetExchangeRatesOfCurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(baseCurrency: String) : LiveData<List<ExchangeRate>> {
        return currencyRepository.getExchangeRatesOfCurrency(baseCurrency)
    }

}