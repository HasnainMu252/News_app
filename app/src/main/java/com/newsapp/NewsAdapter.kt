package com.newsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.ai.client.generativeai.type.content
import com.newsapp.model.NewsArticle

class NewsAdapter(private val newsList: List<NewsArticle>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

        // Set an OnClickListener on the entire itemView to handle item clicks
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", article.title)
                putExtra("description", article.description)
                putExtra("imageUrl", article.urlToImage)
                putExtra("content", article.content) // Assuming you have content in your NewsArticle model
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = newsList.size
}
