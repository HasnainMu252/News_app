package com.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.model.NewsArticle

class news_item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_item)

        class NewsAdapter(private val newsList: List<NewsArticle>) :
            RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

            inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val imageView: ImageView = itemView.findViewById(R.id.imageView)
                val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
                val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_news_item, parent, false)
                return NewsViewHolder(view)
            }

            override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
                val article = newsList[position]
                holder.titleTextView.text = article.title
                holder.descriptionTextView.text = article.description
                Glide.with(holder.itemView.context)
                    .load(article.urlToImage)
                    .into(holder.imageView)
            }

            override fun getItemCount(): Int = newsList.size
        }

    }
}