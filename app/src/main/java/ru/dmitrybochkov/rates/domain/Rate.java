package ru.dmitrybochkov.rates.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
@Entity(indices = {@Index(value = "currency", unique = true)})
public class Rate {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String currency;

    private float value;

    public Rate(String currency, float value) {
        this.currency = currency;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
