package com.example.zarahood.login.model

import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.flow.MutableSharedFlow

class LoginRepo : LoginC.Repository {

    override val navState: MutableSharedFlow<State<LoginNavigation>> = MutableSharedFlow()

    private lateinit var cache:LoginNavigation

    override suspend fun userLoginRequest(userName: String, pass: String) {
        navState.emit(State.publishData(data = LoginNavigation.HOME))
    }

    override fun forgotPasswordClicked() {
        if(::cache.isInitialized) {
            navState.tryEmit(State.publishData(data = LoginNavigation.DEFAULT))
        }else
            return
    }
}