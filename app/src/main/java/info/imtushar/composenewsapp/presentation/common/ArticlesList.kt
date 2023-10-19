package info.imtushar.composenewsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import info.imtushar.composenewsapp.domain.model.Article
import info.imtushar.composenewsapp.presentation.Dimens.ExtraSmallPadding2
import info.imtushar.composenewsapp.presentation.Dimens.MediumPadding1
import info.imtushar.composenewsapp.presentation.home.common.ArticleCard
import java.util.UUID

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
){
    val handlePagingResult = handlePagingResult(articles = articles)

    if (handlePagingResult){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2),

        ){
            items(count = articles.itemCount){
                articles[it]?.let {article ->
                    ArticleCard(article = article, onClick = {onClick(article)})
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean{

    val loadState = articles.loadState

    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading ->{
            ShimmerEffect()
            false
        }
        error != null ->{
            EmptyScreen()
            false
        }
        else ->{
            true
        }
    }
}

@Composable
private fun ShimmerEffect(){
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}