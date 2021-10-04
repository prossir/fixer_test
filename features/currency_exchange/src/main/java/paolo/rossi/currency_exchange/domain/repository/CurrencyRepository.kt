package paolo.rossi.currency_exchange.domain.repository

import androidx.lifecycle.LiveData
import paolo.rossi.currency_exchange.domain.entity.Currency
import paolo.rossi.currency_exchange.domain.entity.ExchangeRate


interface CurrencyRepository {

    suspend fun getCurrencies(filterText: LiveData<String>): LiveData<List<Currency>>
    suspend fun getExchangeRatesOfCurrency(baseCurrency: String): LiveData<List<ExchangeRate>>

}