package com.rmaprojects.testauthsupa.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.apirequeststate.RequestState
import com.rmaprojects.testauthsupa.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _authState = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val authState = _authState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            RequestState.Idle
        )

    fun performRegister(email: String, password: String) {
        viewModelScope.launch {
            _authState.emitAll(repository.register(email, password))
        }
    }

}