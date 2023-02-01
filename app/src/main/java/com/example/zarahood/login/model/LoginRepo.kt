package com.example.zarahood.login.model

import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.flow.MutableSharedFlow

class LoginRepo : LoginC.Repository {

    override val navState: MutableSharedFlow<State<LoginNavigation>> =MutableSharedFlow()

    override suspend fun userLoginRequest(userName: String, pass: String) {
        navState.emit(State.publishData(data = LoginNavigation.HOME))
    }
}