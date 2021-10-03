package paolo.rossi.currency_exchange.domain.repository

import androidx.lifecycle.LiveData
import paolo.rossi.currency_exchange.domain.entity.Currency


interface CurrencyRepository {

    suspend fun getCurrencies(filterText: LiveData<String>): LiveData<List<Currency>>

}