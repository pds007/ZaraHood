package com.example.zarahood.home.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.zarahood.home.profile.uimodels.UserProfileUiModel
import com.example.zarahood.home.profile.di.ProfileDependencyHolder
import com.example.zarahood.home.profile.model.ProfileC
import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repo: ProfileC.Repository,
) : ViewModel() {

    val repoListLiveData: LiveData<State<UserProfileUiModel>> =
        repo.repoList.asLiveData()

    suspend fun userProfileApiRequest() {
        viewModelScope.launch(
            context = Dispatchers.IO
        ) {
            repo.userProfileApiRequest()
        }
    }

    override fun onCleared() {
        super.onCleared()
        ProfileDependencyHolder.destroyProfileComponent()
    }

}