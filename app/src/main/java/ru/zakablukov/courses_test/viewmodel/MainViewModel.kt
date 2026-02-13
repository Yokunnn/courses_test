package ru.zakablukov.courses_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.zakablukov.courses_test.enum.LoadState
import ru.zakablukov.domain.model.Course
import ru.zakablukov.domain.repository.CourseRepository
import ru.zakablukov.domain.util.Request

class MainViewModel(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _coursesResult = MutableStateFlow<List<Course>?>(null)
    val coursesResult: StateFlow<List<Course>?> = _coursesResult

    private val _coursesLoadState = MutableStateFlow<LoadState?>(null)
    val coursesLoadState: StateFlow<LoadState?> = _coursesLoadState

    fun getCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.getCourses().collect { requestState ->
                when (requestState) {
                    is Request.Error -> _coursesLoadState.emit(LoadState.ERROR)
                    is Request.Loading -> _coursesLoadState.emit(LoadState.LOADING)
                    is Request.Success -> {
                        _coursesLoadState.emit(LoadState.SUCCESS)
                        _coursesResult.emit(requestState.data)
                    }
                }
            }
        }
    }

    fun sortCourses() {
        viewModelScope.launch {
            _coursesResult.emit(_coursesResult.value.orEmpty().sortedByDescending { it.publishDate })
        }
    }
}