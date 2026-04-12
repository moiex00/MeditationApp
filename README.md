# MeditationApp - NIT3213 Final Assignment

## Student Details
- **Name:** Abdul Moeez Saleh
- **Student ID:** S8092899
- **Course:** NIT3213 Mobile Application Development - SY

## App Overview
An Android application that integrates with the VU NIT3213 API to authenticate users and display a dashboard of art entities.

## Features
- Login screen with API authentication
- Dashboard screen displaying art entities using RecyclerView
- Details screen showing full information about each artwork
- Dependency Injection using Hilt
- Unit tests for ViewModels

## API Details
- **Base URL:** https://nit3213api.onrender.com/
- **Login Endpoint:** POST /sydney/auth
- **Dashboard Endpoint:** GET /dashboard/{keypass}

## Login Credentials
- **Username:** s8092899
- **Password:** Abdul Moeez

## Tech Stack
- Kotlin
- Hilt for Dependency Injection
- Retrofit for API calls
- RecyclerView for list display
- MVVM Architecture
- LiveData & ViewModel
- Unit Testing with JUnit and Mockito

## How to Build and Run
1. Clone the repository
2. Open in Android Studio
3. Wait for Gradle sync to complete
4. Run the app on an emulator or physical device (API 24+)
5. Login with your student credentials

## Project Structure

app/
├── MeditationApplication.kt  - Hilt Application class
├── NetworkModule.kt          - Hilt DI module for Retrofit
├── ApiService.kt             - Retrofit API interface
├── Repository.kt             - Data repository
├── Entity.kt                 - Data models
├── LoginViewModel.kt         - ViewModel for login
├── DashboardViewModel.kt     - ViewModel for dashboard
├── EntityAdapter.kt          - RecyclerView adapter
├── MainActivity.kt           - Login screen
├── HomeActivity.kt           - Dashboard screen
├── DetailsActivity.kt        - Details screen
└── SettingsActivity.kt       - Settings screen

## Git Repository
https://github.com/moiex00/MeditationApp
