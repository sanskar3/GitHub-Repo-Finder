package com.sanskar.test.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sanskar.test.R
import com.sanskar.test.databinding.ActivityMainBinding
import com.sanskar.test.search.SearchListActivity

const val Search_Key = "search"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = MainViewModel()

        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        initObserver()
    }

    private fun initObserver() {
        mainViewModel.searchClick.observe(this, Observer { searchKeyword ->
            val intent = Intent(this@MainActivity, SearchListActivity::class.java)
            intent.putExtra(Search_Key, searchKeyword)
            startActivity(intent)
        })
    }
}