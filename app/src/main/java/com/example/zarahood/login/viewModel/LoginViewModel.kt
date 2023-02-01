package com.example.zarahood.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.zarahood.login.di.LoginDependencyHolder
import com.example.zarahood.login.model.LoginC
import com.example.zarahood.login.model.LoginNavigation
import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repo: LoginC.Repository,
) : ViewModel() {

    val repoListLiveData: LiveData<State<LoginNavigation>> =
        repo.navState.asLiveData()

    fun userLoginRequest(
        userName: String,
        pass: String
    ) {
        viewModelScope.launch(
            context = Dispatchers.IO
        ) {
            repo.userLoginRequest(userName = userName, pass = pass)
        }
    }

    override fun onCleared() {
        super.onCleared()
        LoginDependencyHolder.destroyLoginComponent()
    }

}