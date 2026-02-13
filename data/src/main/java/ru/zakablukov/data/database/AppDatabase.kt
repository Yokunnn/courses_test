package ru.zakablukov.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zakablukov.data.database.dao.FavouriteCourseDao
import ru.zakablukov.data.database.entity.FavouriteCourseEntity

@Database(entities = [FavouriteCourseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteCourseDao(): FavouriteCourseDao
}