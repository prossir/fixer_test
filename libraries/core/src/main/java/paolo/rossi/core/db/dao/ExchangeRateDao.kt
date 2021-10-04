package paolo.rossi.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.koin.core.component.KoinComponent
import paolo.rossi.core.db.dto.ExchangeRateEntity


@Dao
abstract class ExchangeRateDao : BaseDao<ExchangeRateEntity>, KoinComponent {

    @Query("SELECT * FROM exchangeRate WHERE baseCurrency = :baseCurrency")
    abstract fun findAllByBaseCurrency(baseCurrency: String) : LiveData<List<ExchangeRateEntity>>

}