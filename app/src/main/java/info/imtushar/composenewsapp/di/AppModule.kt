package info.imtushar.composenewsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.imtushar.composenewsapp.data.local.NewsDao
import info.imtushar.composenewsapp.data.local.NewsDatabase
import info.imtushar.composenewsapp.data.local.NewsTypeConvertor
import info.imtushar.composenewsapp.data.manager.LocalUserManagerImpl
import info.imtushar.composenewsapp.data.remote.NewsApi
import info.imtushar.composenewsapp.data.repository.NewsRepositoryImpl
import info.imtushar.composenewsapp.domain.manager.LocalUserManager
import info.imtushar.composenewsapp.domain.repository.NewsRepository
import info.imtushar.composenewsapp.domain.usecases.app_entry.AppEntryUseCases
import info.imtushar.composenewsapp.domain.usecases.app_entry.ReadAppEntry
import info.imtushar.composenewsapp.domain.usecases.app_entry.SaveAppEntry
import info.imtushar.composenewsapp.domain.usecases.news.DeleteArticle
import info.imtushar.composenewsapp.domain.usecases.news.GetArticle
import info.imtushar.composenewsapp.domain.usecases.news.GetNews
import info.imtushar.composenewsapp.domain.usecases.news.NewsUseCases
import info.imtushar.composenewsapp.domain.usecases.news.SearchNews
import info.imtushar.composenewsapp.domain.usecases.news.SelectArticles
import info.imtushar.composenewsapp.domain.usecases.news.UpsertArticle
import info.imtushar.composenewsapp.util.Constants.BASE_URL
import info.imtushar.composenewsapp.util.Constants.NEWS_DB_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) = NewsUseCases(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository),
        upsertArticle = UpsertArticle(newsDao = newsDao),
        deleteArticle = DeleteArticle(newsDao = newsDao),
        selectArticles = SelectArticles(newsDao = newsDao),
        getArticle = GetArticle(newsDao = newsDao)
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {

        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DB_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


}