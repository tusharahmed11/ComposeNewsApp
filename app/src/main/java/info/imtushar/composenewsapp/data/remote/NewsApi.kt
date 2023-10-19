package info.imtushar.composenewsapp.data.remote

import info.imtushar.composenewsapp.data.remote.dto.NewsResponse
import info.imtushar.composenewsapp.util.Constants.API_KEY
import info.imtushar.composenewsapp.util.Constants.PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY,
       // @Query("pageSize") pageSize: Int = PAGE_SIZE,
    ): NewsResponse

}