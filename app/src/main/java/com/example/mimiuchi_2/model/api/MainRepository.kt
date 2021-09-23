package com.example.mimiuchi_2.model.api

import com.example.mimiuchi_2.model.api.ApiService

interface MainRepository {
    fun initApiService(): ApiService
}