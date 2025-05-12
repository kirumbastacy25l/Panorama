package com.kirumbastacy.panoramahotel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirumbastacy.panoramahotel.model.User
import com.kirumbastacy.panoramahotel.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    // Fetch user details by email
    fun fetchUser(email: String) {
        viewModelScope.launch {
            val fetchedUser = repository.getUserByEmail(email)
            _user.postValue(fetchedUser)
        }
    }
}