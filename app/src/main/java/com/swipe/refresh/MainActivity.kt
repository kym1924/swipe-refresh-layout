package com.swipe.refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.swipe.refresh.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSwipeRefreshLayoutListener()
        setSwipeRefreshLayoutColorResources()
        setSwipeCountCollect()
    }

    private fun setSwipeRefreshLayoutListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.upCount()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setSwipeRefreshLayoutColorResources() {
        binding.swipeRefreshLayout.setColorSchemeResources(
            R.color.purple_500,
            R.color.purple_700,
            R.color.purple_200
        )
    }

    private fun setSwipeCountCollect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.swipeCount.collect { count ->
                    setTvSwipeCountText(count)
                }
            }
        }
    }

    private fun setTvSwipeCountText(count: Int) {
        binding.tvSwipeCount.text = String.format(getString(R.string.swipe_count), count)
    }
}
