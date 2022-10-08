package com.movies.themoviestestapp.home.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.movies.themoviestestapp.databinding.ActivityMainBinding
import com.movies.themoviestestapp.home.viewmodels.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.observeTitle.observe(this) { title ->
            binding.layoutAppBar.navTitleTextView.text = title
        }

        viewModel.observeShowBackButton.observe(this) {
            binding.layoutAppBar.navBackImageView.isVisible = it
        }

        binding.layoutAppBar.navBackImageView.setOnClickListener {
            super.onBackPressed()
        }
    }
}
