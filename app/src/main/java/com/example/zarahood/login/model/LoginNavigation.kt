package com.example.zarahood.login.model

import android.content.Context
import android.content.Intent
import com.example.zarahood.home.view.MainActivity


enum class LoginNavigation {
    HOME {
        override fun launch(
            context: Context
        ) {
            context.startActivity(
                Intent(
                    context,
                    MainActivity::class.java
                )
            )
        }
    };
    abstract fun launch(context: Context)
}