package info.imtushar.composenewsapp.domain.repository

import androidx.paging.PagingData
import info.imtushar.composenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>) : Flow<PagingData<Article>>

    fun searchNews(sources: List<String>, searchQuery: String) : Flow<PagingData<Article>>

}