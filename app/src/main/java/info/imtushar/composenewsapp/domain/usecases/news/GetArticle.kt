package info.imtushar.composenewsapp.domain.usecases.news

import info.imtushar.composenewsapp.data.local.NewsDao
import info.imtushar.composenewsapp.domain.model.Article


class GetArticle(
    private val newsDao: NewsDao
) {

    operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url)
    }
}