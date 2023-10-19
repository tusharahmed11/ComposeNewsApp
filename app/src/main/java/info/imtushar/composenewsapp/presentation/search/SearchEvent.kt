package info.imtushar.composenewsapp.presentation.search

import retrofit2.http.Query

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object SearchNews : SearchEvent()
}