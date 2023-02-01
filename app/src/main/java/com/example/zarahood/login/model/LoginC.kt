package com.example.zarahood.login.model

import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.flow.Flow

interface LoginC {

    interface Repository {
        val navState: Flow<State<LoginNavigation>>

       suspend fun userLoginRequest( userName:String, pass:String)
   }

}