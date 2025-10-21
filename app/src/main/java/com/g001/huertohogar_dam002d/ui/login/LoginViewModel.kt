package com.g001.huertohogar_dam002d.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g001.huertohogar_dam002d.data.User
import com.g001.huertohogar_dam002d.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val error: String? = null,
    val user: User? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState(isLoading = true)
            try {
                val user = userRepository.login(username, password)
                if (user != null) {
                    _uiState.value = LoginUiState(loginSuccess = true, user = user)
                } else {
                    _uiState.value = LoginUiState(error = "Invalid credentials")
                }
            } catch (e: Exception) {
                _uiState.value = LoginUiState(error = e.message)
            }
        }
    }
}
