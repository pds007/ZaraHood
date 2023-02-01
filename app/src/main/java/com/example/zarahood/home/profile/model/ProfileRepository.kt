package com.example.zarahood.home.profile.model

import android.util.Log
import com.example.zarahood.home.profile.uimodels.UserProfileUiModel
import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.flow.MutableSharedFlow

class ProfileRepository(
    private val remote:ProfileC.Remote
):ProfileC.Repository {

    override val repoList: MutableSharedFlow<State<UserProfileUiModel>> = MutableSharedFlow()

    override suspend fun userProfileApiRequest() {
        repoList.emit(value = State.publishLoading(isLoading = true))
        try {
            val response: UserProfileUiModel = remote.userProfileApiRequest()
                .let(::UserProfileUiModel)
            repoList.emit(State.publishData(data = response))
        } catch (
            ex: Throwable
        ) {
            Log.e("gvfvtg","msg",ex)
            repoList.emit(State.publishError(e = ex))
        }
    }
}