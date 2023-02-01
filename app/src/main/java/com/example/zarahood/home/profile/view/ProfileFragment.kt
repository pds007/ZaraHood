package com.example.zarahood.home.profile.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.zarahood.R
import com.example.zarahood.utils.processingStates.State
import com.example.zarahood.databinding.FragmentSecondBinding
import com.example.zarahood.home.profile.di.ProfileComponent
import com.example.zarahood.home.profile.di.ProfileDependencyHolder
import com.example.zarahood.home.profile.viewmodel.ProfileVMF
import com.example.zarahood.home.profile.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProfileVMF

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private val component: ProfileComponent
        get() = ProfileDependencyHolder.initProfileComponent()

    override fun onAttach(context: Context) {
        component.inject(fragment = this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUpLiveDataListeners()
        if (
            savedInstanceState == null
        ) {
            getUserProfileData()
        }
        setUpUi()
    }

    private fun setUpUi() {
        binding.retryBtn.setOnClickListener {
            getUserProfileData()
        }
    }

    private fun setUpLiveDataListeners() {
        viewModel.repoListLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Data -> {
                    binding.progress.isGone = true
                    binding.userNameTv.text = resources.getString(
                        R.string.profile_name_tv,
                        state.data.firstname
                    )
                    binding.userAddressTv.text = resources.getString(
                        R.string.profile_dob_tv,
                        state.data.dob
                    )
                    binding.userMobileTv.text = resources.getString(
                        R.string.profile_points_tv,
                        state.data.pointsEarned
                    )
                    binding.retryBtn.isGone = true
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
        binding.userNameTv.isGone = true
        binding.userAddressTv.isGone = true
        binding.userMobileTv.isGone = true
        binding.retryBtn.isVisible = true

    }

    private fun showUiOnLoadingState() {
        binding.progress.isVisible = true
        binding.userNameTv.isGone = true
        binding.userAddressTv.isGone = true
        binding.userMobileTv.isGone = true
        binding.retryBtn.isGone = true
    }

    private fun getUserProfileData() {
        lifecycleScope.launch {
            viewModel.userProfileApiRequest()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}