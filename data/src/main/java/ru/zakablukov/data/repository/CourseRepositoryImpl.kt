package ru.zakablukov.data.repository

import kotlinx.coroutines.flow.Flow
import ru.zakablukov.data.RequestUtils.requestFlow
import ru.zakablukov.data.mapper.toDomain
import ru.zakablukov.data.service.CourseService
import ru.zakablukov.domain.model.Course
import ru.zakablukov.domain.repository.CourseRepository
import ru.zakablukov.domain.util.Request

class CourseRepositoryImpl(
    private val courseService: CourseService
) : CourseRepository {

    override suspend fun getCourses(): Flow<Request<List<Course>>> =
        requestFlow {
            val courses = courseService.getCourses()
            courses.toDomain()
        }

}