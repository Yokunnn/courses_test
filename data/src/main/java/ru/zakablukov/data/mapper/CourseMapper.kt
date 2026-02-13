package ru.zakablukov.data.mapper

import ru.zakablukov.data.database.entity.FavouriteCourseEntity
import ru.zakablukov.data.model.CourseListResponse
import ru.zakablukov.data.model.CourseResponse
import ru.zakablukov.domain.model.Course
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset


fun CourseListResponse.toDomain(): List<Course> =
    courseList.map { it.toDomain() }

fun CourseResponse.toDomain(): Course =
    Course(
        id,
        title,
        description,
        price,
        rate,
        LocalDate.parse(startDate).atStartOfDay().toInstant(ZoneOffset.UTC),
        hasLike,
        LocalDate.parse(publishDate).atStartOfDay().toInstant(ZoneOffset.UTC)
    )

fun FavouriteCourseEntity.toDomain(): Course =
    Course(
        id,
        title,
        description,
        price,
        rate,
        Instant.parse(startDate),
        true,
        Instant.parse(startDate)
    )

fun Course.toEntity(): FavouriteCourseEntity =
    FavouriteCourseEntity(
        id,
        title,
        description,
        price,
        rate,
        startDate.toString(),
        publishDate.toString()
    )