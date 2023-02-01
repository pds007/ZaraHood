package com.example.zarahood.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zarahood.login.model.LoginC

@Suppress("UNCHECKED_CAST")
class LoginVMF(
    private val repo: LoginC.Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return LoginViewModel(repo = repo) as T
    }

}

