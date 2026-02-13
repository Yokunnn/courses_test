package ru.zakablukov.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zakablukov.data.RequestUtils.requestFlow
import ru.zakablukov.data.database.dao.FavouriteCourseDao
import ru.zakablukov.data.mapper.toDomain
import ru.zakablukov.data.mapper.toEntity
import ru.zakablukov.data.service.CourseService
import ru.zakablukov.domain.model.Course
import ru.zakablukov.domain.repository.CourseRepository
import ru.zakablukov.domain.util.Request

class CourseRepositoryImpl(
    private val courseService: CourseService,
    private val favouriteCourseDao: FavouriteCourseDao
) : CourseRepository {

    override suspend fun getCourses(): Flow<Request<List<Course>>> =
        requestFlow {
            val coursesResponse = courseService.getCourses()
            val courses = coursesResponse.toDomain()
            courses.forEach { c ->
                if (c.hasLike) addFavouriteCourse(c)
            }
            courses
        }

    override fun getFavouriteCourses(): Flow<List<Course>> =
        favouriteCourseDao.getFavouriteCourses().map { list -> list.map { it.toDomain() } }

    override suspend fun addFavouriteCourse(course: Course) =
        favouriteCourseDao.upsertFavouriteCourse(course.toEntity())

    override suspend fun deleteFavouriteCourse(id: Int) =
        favouriteCourseDao.deleteCourseById(id)

}