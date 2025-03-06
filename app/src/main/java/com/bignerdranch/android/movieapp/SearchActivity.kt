package com.bignerdranch.android.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.movieapp.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity() {
    private val apiService = RetrofitClient.apiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerView = findViewById(R.id.recyclerView) // Проверь название ID в XML
        adapter = MovieAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val year = intent.getStringExtra("YEAR")

        val query = intent.getStringExtra("QUERY") ?: return

        CoroutineScope(Dispatchers.IO).launch {
            try {
//                val response = apiService.searchMovies(query)

                val response = if (year.isNullOrEmpty()) {
                    apiService.searchMovies(query)
                } else {
                    apiService.searchMovies("$query $year")
                }
                withContext(Dispatchers.Main) {
                    if (response.Search != null) {
                        adapter.setData(response.Search)
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}