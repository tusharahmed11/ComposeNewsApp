package info.imtushar.composenewsapp.domain.usecases.news

import info.imtushar.composenewsapp.data.local.NewsDao
import info.imtushar.composenewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke() : Flow<List<Article>>{
        return newsDao.getArticles()
    }

}