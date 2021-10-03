package paolo.rossi.core.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(obj: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg obj: T)

    @Insert
    suspend fun delete(vararg obj: T)

}