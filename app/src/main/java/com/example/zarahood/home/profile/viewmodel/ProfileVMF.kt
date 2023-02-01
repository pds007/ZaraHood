package com.example.zarahood.home.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zarahood.home.profile.model.ProfileC

@Suppress("UNCHECKED_CAST")
class ProfileVMF(
    private val repo: ProfileC.Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) = ProfileViewModel(repo = repo) as T

}
