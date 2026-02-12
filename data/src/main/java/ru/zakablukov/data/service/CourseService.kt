package ru.zakablukov.data.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.zakablukov.data.model.CourseListResponse

interface CourseService {

    @GET(GET_COURSES_REQUEST)
    suspend fun getCourses(
        @Query(QUERY_ID) id: String = DEFAULT_ID,
        @Query(QUERY_EXPORT) export: String = DEFAULT_EXPORT,
    ): CourseListResponse

    companion object {
        private const val GET_COURSES_REQUEST = "u/0/uc"
        private const val QUERY_ID = "id"
        private const val QUERY_EXPORT = "export"
        private const val DEFAULT_ID = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
        private const val DEFAULT_EXPORT = "download"
    }
}