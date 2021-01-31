package com.sanskar.test.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanskar.test.R
import com.sanskar.test.databinding.ActivitySearchListBinding
import com.sanskar.test.main.Search_Key

class SearchListActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SearchListActivity"
    }

    private lateinit var binding: ActivitySearchListBinding
    private lateinit var searchViewModel: SearchViewModel

    private var searchListAdapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_list)

        intent.getStringExtra(Search_Key)?.let {
            searchViewModel = SearchViewModel(this.application, it)
        }

        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this

        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        initObserver()
    }

    private fun initObserver() {
        searchViewModel.searchSuccess.observe(this, androidx.lifecycle.Observer { searchItemsList ->
            Log.d(TAG, "Search::Search Success")
            binding.progressBar.visibility = View.GONE
            searchListAdapter = SearchListAdapter(searchItemsList)
            binding.recycleView.adapter = searchListAdapter
        })

        searchViewModel.searchFailure.observe(this, androidx.lifecycle.Observer {
            Log.d(TAG, "Search::Search Failure")
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this@SearchListActivity, "something went wrong", Toast.LENGTH_LONG).show()
        })
    }
}