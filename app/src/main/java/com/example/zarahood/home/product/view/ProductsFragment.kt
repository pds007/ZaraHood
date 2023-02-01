package com.example.zarahood.home.product.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.zarahood.R
import com.example.zarahood.databinding.FragmentFirstBinding
import com.example.zarahood.home.product.di.ProductComponent
import com.example.zarahood.home.product.di.ProductsDependencyHolder
import com.example.zarahood.home.product.viewmodel.ProductsVMF
import com.example.zarahood.home.product.viewmodel.ProductsViewModel
import com.example.zarahood.utils.processingStates.State
import javax.inject.Inject

class ProductsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProductsVMF

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ProductsViewModel::class.java]
    }

    private val component: ProductComponent
        get() = ProductsDependencyHolder.initProductsComponent()

    private val adp: ProductListAdapter by lazy {
        ProductListAdapter()
    }

    override fun onAttach(context: Context) {
        component.inject(fragment = this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFirstBinding.inflate(
        inflater,
        container,
        false
    ).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi(view = view)
        setUpLiveDataListeners()
        setUpAdapter()
        if (savedInstanceState == null) {
            viewModel.getProducts()
        }
    }

    private fun setUpAdapter() {
        binding.productsRecyclerView.adapter = adp
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setUpUi(view: View) {
        _binding = FragmentFirstBinding.bind(view)
        (requireActivity()).actionBar
            ?.setTitle(R.string.product_tab_toolbar_title)
        binding.retryBtn.setOnClickListener {
            viewModel.getProducts()
        }
    }

    private fun setUpLiveDataListeners() {
        viewModel.repoListLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Data -> {
                    binding.progress.isGone = true
                    binding.retryBtn.isGone = true
                    binding.productsRecyclerView.isVisible = true
                    adp.submitList(state.data)
                }
                is State.Error -> {
                    binding.progress.isGone = true
                    binding.productsRecyclerView.isGone = true
                    binding.retryBtn.isVisible = true
                }
                is State.Loading -> {
                    binding.productsRecyclerView.isGone = true
                    binding.progress.isVisible = true
                    binding.retryBtn.isGone = true
                }
            }
        }
    }
}