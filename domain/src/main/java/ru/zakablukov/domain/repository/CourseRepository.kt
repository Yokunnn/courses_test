package ru.zakablukov.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zakablukov.domain.model.Course
import ru.zakablukov.domain.util.Request

interface CourseRepository {

    suspend fun getCourses(): Flow<Request<List<Course>>>

    fun getFavouriteCourses(): Flow<List<Course>>

    suspend fun addFavouriteCourse(course: Course)

    suspend fun deleteFavouriteCourse(id: Int)
}