package com.example.zarahood.home.view

import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zarahood.R
import com.example.zarahood.home.product.view.ProductsFragment
import com.example.zarahood.home.profile.view.ProfileFragment

class MainActivityViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm,lifecycle) {

    companion object {
        private const val TAB_COUNT = 2
        private const val TAB_POSITION_PRODUCTS = 0
        private const val TAB_POSITION_PROFILE = 1
    }

    override fun createFragment(
        position: Int
    ) = when (position) {
        TAB_POSITION_PRODUCTS -> {
            ProductsFragment()
        }
        TAB_POSITION_PROFILE -> {
            ProfileFragment()
        }
        else -> {
            throw IllegalArgumentException(
                "only 2 tabs: Ui constraints violated"
            )
        }
    }

    override fun getItemCount() = TAB_COUNT

    @StringRes
    fun getTabTitle(
        @IntRange(
            from = TAB_POSITION_PRODUCTS.toLong(),
            to = TAB_POSITION_PROFILE.toLong()
        )
        position: Int
    ) = when(position) {
        TAB_POSITION_PRODUCTS -> {
            R.string.product_tab_toolbar_title
        }
        TAB_POSITION_PROFILE -> {
            R.string.profile_title
        }
        else -> {
            throw IllegalArgumentException(
                "only 2 tabs: Ui constraints violated"
            )
        }
    }

    @StringRes
    fun getToolBarTitle(
        @IntRange(
            from = TAB_POSITION_PRODUCTS.toLong(),
            to = TAB_POSITION_PROFILE.toLong()
        )
        position: Int = TAB_POSITION_PRODUCTS
    ) = when(position) {
        TAB_POSITION_PRODUCTS -> {
            R.string.product_tab_toolbar_title
        }
        TAB_POSITION_PROFILE -> {
            R.string.profile_title
        }
        else -> {
            throw IllegalArgumentException(
                "only 2 tabs: Ui constraints violated"
            )
        }
    }

}