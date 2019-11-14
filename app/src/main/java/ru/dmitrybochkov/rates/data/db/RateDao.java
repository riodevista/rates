package ru.dmitrybochkov.rates.data.db;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import ru.dmitrybochkov.rates.domain.Rate;

@Dao
public abstract class RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertOrReplace(List<Rate> rates);

    @Transaction
    public void clearAndInsert(List<Rate> rates) {
        deleteAll();
        insertOrReplace(rates);
    }

    @Update
    public abstract void update(Rate... rates);

    @Delete
    public abstract void delete(Rate... rates);


    @Query("SELECT * FROM Rate")
    public abstract LiveData<List<Rate>> loadAll();


    @Query("DELETE FROM Rate")
    public abstract void deleteAll();

    @Query("SELECT * FROM Rate")
    public abstract List<Rate> getAll();

    @Query("SELECT * FROM Rate WHERE id = :RateId LIMIT 1")
    public abstract Rate getById(long RateId);
}
