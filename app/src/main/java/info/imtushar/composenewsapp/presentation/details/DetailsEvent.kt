package info.imtushar.composenewsapp.presentation.details

import info.imtushar.composenewsapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
 }