package com.sanskar.test.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanskar.test.R
import com.sanskar.test.databinding.SearchListItemBinding

class SearchListAdapter(private val searchListItems: MutableList<SearchListItem>) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SearchListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.search_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchListItem = searchListItems[position]
        holder.bind(searchListItem)
    }

    override fun getItemCount(): Int {
        return searchListItems.size
    }

    inner class ViewHolder(private val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(searchListItem: SearchListItem) {
            binding.viewModel = SearchItemViewModel(searchListItem)
        }
    }
}