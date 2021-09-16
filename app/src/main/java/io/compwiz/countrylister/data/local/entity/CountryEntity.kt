package io.compwiz.countrylister.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "countries")
data class CountryEntity(
    val name: String,
    val region: String,
    val subRegion: String,
    val capitalCity: String,
    val alphaCode: String,
    val population: Int,
    val imageUrl: String
): Parcelable