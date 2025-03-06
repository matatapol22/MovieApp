package com.bignerdranch.android.movieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.movieapp.data.MovieDao
import com.bignerdranch.android.movieapp.data.MovieDatabase
import com.bignerdranch.android.movieapp.network.MovieItem
import com.bignerdranch.android.movieapp.ui.theme.MovieAppTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var movieDao: MovieDao

    private lateinit var adapter: MovieAdapter
    private val movieList = mutableListOf<MovieItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = MovieDatabase.getDatabase(this)
        movieDao = db.movieDao()
        adapter = MovieAdapter()

        findViewById<RecyclerView>(R.id.recycler_view_main).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        // Проверяем, были ли переданы данные из SearchActivity
        val title = intent.getStringExtra("MOVIE_TITLE")
        val year = intent.getStringExtra("MOVIE_YEAR")
        val poster = intent.getStringExtra("MOVIE_POSTER")

        adapter.setData(movieList) // Установка данных в адаптер

        adapter.listener = object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: MovieItem) {
                movieList.remove(movie) // Удаляем фильм из списка
                adapter.setData(movieList) // Обновляем список
                Toast.makeText(this@MainActivity, "${movie.Title} удален", Toast.LENGTH_SHORT).show()
            }
        }

        if (title != null && year != null && poster != null) {
            val movie = MovieItem(title, year, poster)
            movieList.add(movie) // Добавляем фильм в список
            adapter.setData(movieList) // Обновляем адаптер со всем списком
        }

        findViewById<FloatingActionButton>(R.id.fab_add).setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}