package com.newsapp

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.model.NewsArticle
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _newsArticles = MutableLiveData<List<NewsArticle>>()
    val newsArticles: LiveData<List<NewsArticle>> get() = _newsArticles

    fun getTopHeadlines() {
        viewModelScope.launch {
            try {
            
                val response = RetrofitInstance.api.getTopHeadlines("us", "6980aba3e728462fad5f1a670e591332")
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.articles?.let { articles ->
                        _newsArticles.value = articles
                    }
                } else {

                }
            } catch (e: Exception) {

            }
        }
    }
}
