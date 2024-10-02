package com.vasifgumbatov.weatherapp.practice

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(
    private val university: University
) : ViewModel() {

    fun getUniversityInfo(): University {
        return university
    }
}