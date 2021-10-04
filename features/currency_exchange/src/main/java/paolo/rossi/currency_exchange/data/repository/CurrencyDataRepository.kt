package paolo.rossi.currency_exchange.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import paolo.rossi.currency_exchange.data.data_source.local.CurrencyLocalDataSource
import paolo.rossi.currency_exchange.data.data_source.remote.CurrencyRemoteDataSource
import paolo.rossi.currency_exchange.data.mapper.CurrencyLocalMapper
import paolo.rossi.currency_exchange.data.mapper.CurrencyRemoteMapper
import paolo.rossi.currency_exchange.data.mapper.ExchangeRateLocalMapper
import paolo.rossi.currency_exchange.data.mapper.ExchangeRateRemoteMapper
import paolo.rossi.currency_exchange.domain.entity.Currency
import paolo.rossi.currency_exchange.domain.entity.ExchangeRate
import paolo.rossi.currency_exchange.domain.repository.CurrencyRepository


class CurrencyDataRepository(
    private val currencyLocalMapper: CurrencyLocalMapper,
    private val currencyRemoteMapper: CurrencyRemoteMapper,
    private val exchangeRateLocalMapper: ExchangeRateLocalMapper,
    private val exchangeRateRemoteMapper: ExchangeRateRemoteMapper,
    private val localDataSource: CurrencyLocalDataSource,
    private val remoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {

    override suspend fun getCurrencies(filterText: LiveData<String>): LiveData<List<Currency>> {
        // we call from the API to refresh the data
        localDataSource.insertAllCurrencies(currencyRemoteMapper.map(remoteDataSource.getCurrencies()))
        // we observe the database as its being populated
        return Transformations.map(localDataSource.retrieveLiveAllFiltered(filterText)) {
            currencyLocalMapper.map(it)
        }
    }

    override suspend fun getExchangeRatesOfCurrency(baseCurrency: String): LiveData<List<ExchangeRate>> {
        localDataSource.insertAllExchangeRates(exchangeRateRemoteMapper.map(remoteDataSource.getExchangeRates(baseCurrency)))
        return Transformations.map(localDataSource.retrieveLiveByBaseCurrency(baseCurrency)) {
            exchangeRateLocalMapper.map(it)
        }
    }

}