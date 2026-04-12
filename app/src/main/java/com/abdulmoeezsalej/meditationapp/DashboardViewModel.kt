package com.abdulmoeezsalej.meditationapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> = _entities

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchDashboard(keypass: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getDashboard(keypass)
                _entities.value = response.entities
            } catch (e: Exception) {
                _error.value = e.message ?: "Something went wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }
}