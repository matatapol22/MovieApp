package com.bignerdranch.android.movieapp

import android.content.Intent
import android.os.Bundle
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
import com.bignerdranch.android.movieapp.data.MovieDao
import com.bignerdranch.android.movieapp.data.MovieDatabase
import com.bignerdranch.android.movieapp.ui.theme.MovieAppTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = MovieDatabase.getDatabase(this)
        movieDao = db.movieDao()

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