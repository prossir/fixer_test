package paolo.rossi.currency_exchange.data.data_source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import paolo.rossi.core.db.model.CurrencyEntity
import paolo.rossi.core.db.provider.DaoProvider


class CurrencyLocalDataSource(
    daoProvider: DaoProvider
) {

    private val currencyDao = daoProvider.getCurrencyDao()

    suspend fun insertAll(currencies: List<CurrencyEntity>) {
        currencyDao.insertAll(currencies)
    }

    suspend fun retrieveLiveAllFiltered(filterText: LiveData<String>) : LiveData<List<CurrencyEntity>> {
        return Transformations.switchMap(filterText) {
            currencyDao.findAllFiltered(if (it.isNullOrBlank()) null else "%$it%")
        }
    }

}