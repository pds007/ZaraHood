package com.example.zarahood.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.zarahood.R
import com.example.zarahood.databinding.ActivityLoginBinding
import com.example.zarahood.login.di.LoginComponent
import com.example.zarahood.login.di.LoginDependencyHolder
import com.example.zarahood.login.model.LoginNavigation
import com.example.zarahood.login.viewModel.LoginVMF
import com.example.zarahood.login.viewModel.LoginViewModel
import com.example.zarahood.utils.processingStates.State
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: LoginVMF

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
    }

    private val component: LoginComponent
        get() = LoginDependencyHolder.initLoginComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(activity = this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpUi()
        viewModel.repoListLiveData.observe(this) { state ->
            when (state) {
                is State.Data -> {
                    showUiOnSuccessState(state = state)
                }

                is State.Error -> {
                    showUiOnErrorState()
                }

                is State.Loading -> {
                    showUiOnLoadingState()
                }
            }
        }
    }

    private fun showUiOnSuccessState(state: State.Data<LoginNavigation>) {
        when (state.data.name) {

            LoginNavigation.HOME.toString() -> {
                state.data.launch(context = this)
                binding.retryBtn.isGone = true
                binding.progress.isGone = true
                binding.usernameEdt.isVisible = true
                binding.userPassEdt.isVisible = true
                binding.loginBtn.isVisible = true
                binding.forgotPassTv.isVisible = true
            }

            LoginNavigation.DEFAULT.toString() -> {
                binding.retryBtn.isGone = true
                binding.progress.isGone = true
                binding.usernameEdt.isVisible = true
                binding.userPassEdt.isVisible = true
                binding.loginBtn.isVisible = true
                binding.forgotPassTv.isGone = true
            }

            else -> {
                throw (IllegalStateException("only default and home screens exists from Login Flow"))
            }
        }
    }

    private fun showUiOnErrorState() {
        binding.progress.isGone = true
        binding.usernameEdt.isGone = true
        binding.userPassEdt.isGone = true
        binding.loginBtn.isGone = true
        binding.forgotPassTv.isGone = true
        binding.retryBtn.isVisible = true
    }

    private fun showUiOnLoadingState() {
        binding.progress.isVisible = true
        binding.usernameEdt.isGone = true
        binding.userPassEdt.isGone = true
        binding.loginBtn.isGone = true
        binding.retryBtn.isGone = true
    }

    private fun setUpUi() {

        binding.forgotPassTv.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.forgot_pass_click),
                Toast.LENGTH_SHORT
            ).show()
            viewModel.forgotPasswordClicked()
        }

        binding.loginBtn.setOnClickListener {
            userLoginFlowValidation()
        }
    }

    private fun userLoginFlowValidation() {
        val isUsernameValid = validateUserNameField(binding.usernameEdt.text?.toString().orEmpty())
        val isPasswordValid = validatePasswordField(binding.userPassEdt.text?.toString().orEmpty())
        if (
            isUsernameValid.not() || isPasswordValid.not()
        ) {
            Log.d(TAG, getString(R.string.failed_to_verify))
            return
        }
        viewModel.userLoginRequest(
            userName = binding.usernameEdt.text?.toString().orEmpty(),
            pass = binding.userPassEdt.text?.toString().orEmpty()
        )
    }

    private fun validatePasswordField(pass: String): Boolean {
        if (
            pass.isBlank()
        ) {
            Toast.makeText(
                this,
                getString(R.string.pass_empty),
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (pass.length < 5) {
            Toast.makeText(
                this,
                getString(R.string.pass_length_error, pass.length),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun validateUserNameField(userName: String): Boolean {
        if (
            userName.isBlank()
        ) {
            Toast.makeText(
                this,
                getString(R.string.name_error_empty_text),
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (
            userName.contains("@", false).not()
        ) {
            Toast.makeText(
                this,
                getString(R.string.name_error_text),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}