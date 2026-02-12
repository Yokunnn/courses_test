package ru.zakablukov.domain.model

import java.time.Instant

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rate: String,
    val startDate: Instant,
    val hasLike: Boolean,
    val publishDate: Instant
)