package com.sanskar.test.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class SearchViewModel(application: Application, searchKeyword: String) : AndroidViewModel(application) {

    private var searchList: MutableList<SearchListItem> = ArrayList()
    val searchSuccess = MutableLiveData<MutableList<SearchListItem>>()
    val searchFailure = MutableLiveData<Void>()

    init {
        search(searchKeyword)
    }

    private fun search(searchKeyword: String) {
        Log.d("SearchViewModel", "Search::Searching for keyword")
        val stringRequest = StringRequest(Request.Method.GET, "https://api.github.com/search/repositories?sort=stars&order=desc&q=$searchKeyword",
                Response.Listener { response ->
                    try {
                        val responseJson = JSONObject(response)
                        val items = responseJson.getJSONArray("items")
                        for (i in 0 until items.length()) {
                            val item = items.getJSONObject(i)
                            val searchListItem = SearchListItem(item.getString("name"), item.getString("language"), item.getJSONObject("owner").getString("avatar_url"), item.getInt("stargazers_count"))
                            searchList.add(searchListItem)
                        }
                        searchSuccess.value = searchList
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener {
            searchFailure.value = null
        })
        val requestQueue = Volley.newRequestQueue(getApplication())
        requestQueue.add(stringRequest)
    }
}