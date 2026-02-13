package ru.zakablukov.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.zakablukov.data.database.entity.FavouriteCourseEntity

@Dao
interface FavouriteCourseDao {

    @Query(SELECT_ALL_FAV_COURSES)
    fun getFavouriteCourses(): Flow<List<FavouriteCourseEntity>>

    @Upsert
    suspend fun upsertFavouriteCourse(courseEntity: FavouriteCourseEntity)

    @Query(DELETE_COURSE_BY_ID)
    suspend fun deleteCourseById(id: Int)

    @Query(SELECT_FAVS_IDS)
    suspend fun getFavouritesIds(): List<Int>

    companion object {
        private const val SELECT_ALL_FAV_COURSES = "SELECT * FROM favourite_course"
        private const val DELETE_COURSE_BY_ID = "DELETE FROM favourite_course WHERE id LIKE :id"
        private const val SELECT_FAVS_IDS = "SELECT id FROM favourite_course"
    }
}