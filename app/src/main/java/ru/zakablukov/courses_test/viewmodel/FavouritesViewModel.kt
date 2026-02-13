package ru.zakablukov.courses_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.zakablukov.domain.model.Course
import ru.zakablukov.domain.repository.CourseRepository

class FavouritesViewModel(
    private val courseRepository: CourseRepository
) : ViewModel() {

    val favouriteCourses = courseRepository.getFavouriteCourses()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun deleteFavCourse(course: Course) {
        viewModelScope.launch { courseRepository.deleteFavouriteCourse(course.id) }
    }
}