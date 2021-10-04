package paolo.rossi.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.koin.core.component.KoinComponent
import paolo.rossi.core.db.dto.CurrencyEntity


@Dao
abstract class CurrencyDao : BaseDao<CurrencyEntity>, KoinComponent {

    @Transaction
    open suspend fun insertOrUpdate(entity: CurrencyEntity) {
        val item = findById(entity.abbreviation)
        if (item == null) {
            insert(entity)
        } else {
            update(entity)
        }
    }

    @Query("SELECT * FROM currency WHERE abbreviation = :abbreviation")
    abstract suspend fun findById(abbreviation: String): CurrencyEntity?

    @Query("SELECT c.* " +
            "FROM currency c " +
            "WHERE CASE WHEN :filter IS NULL THEN 1 ELSE UPPER(c.abbreviation) LIKE UPPER(:filter) OR UPPER(c.name) LIKE UPPER(:filter) END")
    abstract fun findAllFiltered(filter: String?) : LiveData<List<CurrencyEntity>>

    @Query("DELETE FROM currency")
    abstract suspend fun cleanTable()

}