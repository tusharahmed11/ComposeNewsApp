package info.imtushar.composenewsapp.domain.usecases.news

import androidx.paging.PagingData
import info.imtushar.composenewsapp.domain.model.Article
import info.imtushar.composenewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(source: List<String>) : Flow<PagingData<Article>> {
        return newsRepository.getNews(source)
    }
}