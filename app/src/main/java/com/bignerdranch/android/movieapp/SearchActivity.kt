package com.bignerdranch.android.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.movieapp.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity() {
    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val query = intent.getStringExtra("QUERY") ?: return

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.searchMovies(query)
            withContext(Dispatchers.Main) {
                // Покажи список фильмов
            }
        }
    }
}