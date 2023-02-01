package com.example.zarahood.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.zarahood.R
import com.example.zarahood.databinding.ActivityLoginBinding
import com.example.zarahood.login.di.LoginComponent
import com.example.zarahood.login.di.LoginDependencyHolder
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
                    state.data.launch(context = this)
                    binding.retryBtn.isGone = true
                    binding.progress.isGone = true
                    binding.usernameEdt.isVisible = true
                    binding.userPassEdt.isVisible = true
                    binding.loginBtn.isVisible = true
                    binding.forgotPassTv.isVisible = true
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
        binding.loginBtn.setOnClickListener {
            val username = binding.usernameEdt.text?.toString().orEmpty()
            val password = binding.userPassEdt.text?.toString().orEmpty()
            if (
                username.isBlank()
                ||
                password.isBlank()
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.pass_empty),
                    Toast.LENGTH_SHORT
                ).show()
                //return@setOnClickListener
            }
            viewModel.userLoginRequest(userName = username, pass = password)
        }
    }
}