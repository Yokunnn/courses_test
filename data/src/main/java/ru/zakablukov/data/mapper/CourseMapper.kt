package ru.zakablukov.data.mapper

import ru.zakablukov.data.model.CourseResponse
import ru.zakablukov.domain.model.Course
import java.time.LocalDate
import java.time.ZoneOffset


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