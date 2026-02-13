package ru.zakablukov.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_course")
data class FavouriteCourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val publishDate: String
)
