package com.example.zarahood.home.profile.model

import com.example.zarahood.api.models.UserProfileDTO
import com.example.zarahood.home.profile.uimodels.UserProfileUiModel
import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.flow.Flow

interface ProfileC {
    interface Repository {
        val repoList: Flow<State<UserProfileUiModel>>
        suspend fun userProfileApiRequest()
    }

    interface Remote {
        suspend fun userProfileApiRequest(): UserProfileDTO
    }
}
