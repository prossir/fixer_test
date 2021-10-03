package paolo.rossi.currency_exchange.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import paolo.rossi.currency_exchange.data.data_source.local.CurrencyLocalDataSource
import paolo.rossi.currency_exchange.data.data_source.remote.CurrencyRemoteDataSource
import paolo.rossi.currency_exchange.data.mapper.CurrencyLocalMapper
import paolo.rossi.currency_exchange.data.mapper.CurrencyRemoteMapper
import paolo.rossi.currency_exchange.domain.entity.Currency
import paolo.rossi.currency_exchange.domain.repository.CurrencyRepository


class CurrencyDataRepository(
    private val localMapper: CurrencyLocalMapper,
    private val remoteMapper: CurrencyRemoteMapper,
    private val localDataSource: CurrencyLocalDataSource,
    private val remoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {

    override suspend fun getCurrencies(filterText: LiveData<String>): LiveData<List<Currency>> {
        // we call from the API the data
        localDataSource.insertAll(remoteMapper.map(remoteDataSource.getCurrencies()))
        // we observe the database as its being populated
        return Transformations.map(localDataSource.retrieveLiveAllFiltered(filterText)) {
            localMapper.map(it)
        }
    }



}