package ru.zakablukov.domain.model

import java.util.Date

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rate: String,
    val startDate: Date,
    val hasLike: Boolean,
    val publishDate: Date
)