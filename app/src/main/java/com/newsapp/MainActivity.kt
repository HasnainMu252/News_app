package com.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.newsArticles.observe(this, Observer { articles ->
            adapter = NewsAdapter(articles, this@MainActivity)  // Pass the activity context
            recyclerView.adapter = adapter
        })

        // Fetch top headlines
        viewModel.getTopHeadlines()

        // Setup Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Logic for Home option, stay on MainActivity
                    viewModel.getTopHeadlines() // Refresh top headlines if needed
                    true
                }

                R.id.navigation_Fav -> {
                    // Start SearchActivity
                    val intent = Intent(this, Search::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
