package ru.zakablukov.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.zakablukov.data.database.entity.FavouriteCourseEntity

@Dao
interface FavouriteCourseDao {

    @Query(SELECT_ALL_FAV_COURSES)
    suspend fun getFavouriteCourses(): List<FavouriteCourseEntity>

    @Upsert
    suspend fun upsertFavouriteCourse(courseEntity: FavouriteCourseEntity)

    @Query(DELETE_COURSE_BY_ID)
    suspend fun deleteCourseById(id: Int)

    companion object {
        private const val SELECT_ALL_FAV_COURSES = "SELECT * FROM favourite_course"
        private const val DELETE_COURSE_BY_ID = "DELETE FROM favourite_course WHERE id LIKE :id"
    }
}