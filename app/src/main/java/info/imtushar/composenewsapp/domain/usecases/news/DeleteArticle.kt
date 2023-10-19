package info.imtushar.composenewsapp.domain.usecases.news

import info.imtushar.composenewsapp.data.local.NewsDao
import info.imtushar.composenewsapp.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article){
        newsDao.delete(article)
    }
}