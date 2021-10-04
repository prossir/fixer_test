package paolo.rossi.currency_exchange.data.data_source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import paolo.rossi.core.db.dto.CurrencyEntity
import paolo.rossi.core.db.dto.ExchangeRateEntity
import paolo.rossi.core.db.provider.DaoProvider


class CurrencyLocalDataSource(
    daoProvider: DaoProvider
) {

    private val currencyDao = daoProvider.getCurrencyDao()
    private val exchangeRateDao = daoProvider.getExchangeRateDao()

    fun retrieveLiveAllFiltered(filterText: LiveData<String>) : LiveData<List<CurrencyEntity>> {
        return Transformations.switchMap(filterText) {
            currencyDao.findAllFiltered(if (it.isNullOrBlank()) null else "%$it%")
        }
    }

    suspend fun insertAllCurrencies(currencies: List<CurrencyEntity>) {
        currencyDao.insertAll(currencies)
    }

    fun retrieveLiveByBaseCurrency(baseCurrency: String): LiveData<List<ExchangeRateEntity>> {
        return exchangeRateDao.findAllByBaseCurrency(baseCurrency)
    }

    suspend fun insertAllExchangeRates(exchangeRates: List<ExchangeRateEntity>) {
        exchangeRateDao.insertAll(exchangeRates)
    }

}