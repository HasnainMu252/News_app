package com.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("imageUrl")
        val content = intent.getStringExtra("content")

        binding.titleTextView.text = title
        binding.descriptionTextView.text = description
        Glide.with(this)
            .load(imageUrl)
            .into(binding.imageView)
        binding.contentTextView.text = content

        val additionalContent = "Additional content fetched or generated dynamically."
        binding.contentTextView.append("\n\n$additionalContent")
    }
}
