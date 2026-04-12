package com.abdulmoeezsalej.meditationapp

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(username: String, password: String): LoginResponse {
        return apiService.login(LoginRequest(username, password))
    }

    suspend fun getDashboard(keypass: String): DashboardResponse {
        return apiService.getDashboard(keypass)
    }
}