package com.abdulmoeezsalej.meditationapp

data class Entity(
    val artworkTitle: String = "",
    val artist: String = "",
    val medium: String = "",
    val year: Int = 0,
    val description: String = ""
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)