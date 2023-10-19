package info.imtushar.composenewsapp.domain.usecases.news

import androidx.paging.PagingData
import info.imtushar.composenewsapp.domain.model.Article
import info.imtushar.composenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>, searchQuery: String) : Flow<PagingData<Article>>{
        return newsRepository.searchNews(sources,searchQuery)
    }

}