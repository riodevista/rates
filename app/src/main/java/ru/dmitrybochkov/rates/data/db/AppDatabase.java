package ru.dmitrybochkov.rates.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.dmitrybochkov.rates.domain.Rate;

@Database(entities = {Rate.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract RateDao rateDao();

}
