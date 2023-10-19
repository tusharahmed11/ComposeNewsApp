package info.imtushar.composenewsapp.presentation.bookmark

import info.imtushar.composenewsapp.domain.model.Article

data class BookmarkState (
    val articles: List<Article> = emptyList()
)