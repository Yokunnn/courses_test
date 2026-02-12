package ru.zakablukov.data.model

import com.google.gson.annotations.SerializedName

data class CourseListResponse(
    @SerializedName("courses")
    val courseList: List<CourseResponse>
)
