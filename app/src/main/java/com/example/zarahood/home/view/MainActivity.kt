package com.example.zarahood.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.zarahood.R
import com.example.zarahood.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter: MainActivityViewPagerAdapter
        get() {
            return (binding.viewPager.adapter as? MainActivityViewPagerAdapter)
                ?: throw NullPointerException(
                    getString(R.string.viewpager_adapter_not_found_error_text)
                )
        }

    private val tabSwitchListener by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(
                position: Int
            ) {
                super.onPageSelected(position)
                val title = adapter.getToolBarTitle(
                    position = position
                )

                supportActionBar?.setTitle(title)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViewPager()
    }

    override fun onDestroy() {
        binding.viewPager.unregisterOnPageChangeCallback(tabSwitchListener)
        super.onDestroy()
    }

    private fun setUpViewPager() {
        binding.viewPager.adapter = MainActivityViewPagerAdapter(
            fm = supportFragmentManager,
            lifecycle = lifecycle
        )
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            adapter.getTabTitle(
                position = position
            ).let(tab::setText)

        }.attach()

        binding.viewPager.registerOnPageChangeCallback(tabSwitchListener)

    }

    companion object {
        const val TAG = "MainActivity"
    }
}