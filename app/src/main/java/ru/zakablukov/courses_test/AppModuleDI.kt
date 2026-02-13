package ru.zakablukov.courses_test

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.zakablukov.courses_test.viewmodel.AuthViewModel
import ru.zakablukov.courses_test.viewmodel.FavouritesViewModel
import ru.zakablukov.courses_test.viewmodel.MainViewModel

val appModuleDI = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        FavouritesViewModel(get())
    }
    viewModel {
        AuthViewModel()
    }
}