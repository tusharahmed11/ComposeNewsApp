package info.imtushar.composenewsapp.presentation.search

import androidx.paging.PagingData
import info.imtushar.composenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow


data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>> ? = null
){

}
