package ru.zakablukov.courses_test.viewmodel

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    fun validFields(email: String, password: String): Boolean =
        !email.isEmpty() && !password.isEmpty()
                && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

}