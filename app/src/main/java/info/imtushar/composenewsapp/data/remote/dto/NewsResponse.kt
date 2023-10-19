package info.imtushar.composenewsapp.data.remote.dto

import info.imtushar.composenewsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)