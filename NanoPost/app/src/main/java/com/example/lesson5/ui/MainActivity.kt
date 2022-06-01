package com.example.lesson5.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.lesson5.R
import com.example.lesson5.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    private val navHostFragment by lazy {
        binding.navHostFragment.getFragment<NavHostFragment>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNav.setupWithNavController(
            navHostFragment.navController
        )
        navHostFragment.navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.authFragment -> binding.bottomNav.isVisible = false
                R.id.profileFragment2 -> binding.bottomNav.isVisible = true
                R.id.feedFragment3 -> binding.bottomNav.isVisible = true
                R.id.imagesFragment -> binding.bottomNav.isVisible = true
            }
        }
    }
}