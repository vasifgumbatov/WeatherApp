package com.vasifgumbatov.weatherapp.practice

import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Address @Inject constructor(var address: String)

class Student @Inject constructor(var address: Address)

class Teacher @Inject constructor(var address: Address)

class University @Inject constructor(
    private var address: Address,
    private var student: Student,
    private var teacher: Teacher,
){

}