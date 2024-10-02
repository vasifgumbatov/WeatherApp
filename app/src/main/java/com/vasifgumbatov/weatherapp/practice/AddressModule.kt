package com.vasifgumbatov.weatherapp.practice

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AddressModule {

    @Provides
    @Named("student")
    fun provideStudent(): Address = Address("Baku")

    @Provides
    @Named("teacher")
    fun provideTeacher(): Address = Address("Teacher")

}