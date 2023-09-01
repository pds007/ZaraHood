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
    },

    /*
    Different state of Login UI when user clicks on forgot password,
    represents same login screen but with minor changes like
    itself can be made invisible/can have its own flow.
     */

    DEFAULT {
        override fun launch(context: Context) {
        }
    };

    abstract fun launch(context: Context)
}