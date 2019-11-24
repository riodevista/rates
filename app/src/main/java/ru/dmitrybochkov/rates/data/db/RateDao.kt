package ru.dmitrybochkov.rates.data.db


import androidx.lifecycle.LiveData
import androidx.room.*
import ru.dmitrybochkov.rates.domain.Rate

@Dao
abstract class RateDao {

    @get:Query("SELECT * FROM Rate")
    abstract val all: List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(rates: List<Rate>)

    @Transaction
    open fun clearAndInsert(rates: List<Rate>) {
        deleteAll()
        insertOrReplace(rates)
    }

    @Update
    abstract fun update(vararg rates: Rate)

    @Delete
    abstract fun delete(vararg rates: Rate)


    @Query("SELECT * FROM Rate")
    abstract fun loadAll(): LiveData<List<Rate>>


    @Query("DELETE FROM Rate")
    abstract fun deleteAll()

    @Query("SELECT * FROM Rate WHERE id = :RateId LIMIT 1")
    abstract fun getById(RateId: Long): Rate
}
