package ru.dmitrybochkov.rates.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
@Entity(indices = [Index(value = ["currency"], unique = true)])
class Rate(var currency: String, var value: Float) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
