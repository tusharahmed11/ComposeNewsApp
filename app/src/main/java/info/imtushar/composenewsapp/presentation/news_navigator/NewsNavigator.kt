package info.imtushar.composenewsapp.presentation.news_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import info.imtushar.composenewsapp.R
import info.imtushar.composenewsapp.domain.model.Article
import info.imtushar.composenewsapp.presentation.bookmark.BookmarkScreen
import info.imtushar.composenewsapp.presentation.bookmark.BookmarkViewModel
import info.imtushar.composenewsapp.presentation.details.DetailsScreen
import info.imtushar.composenewsapp.presentation.details.DetailsViewModel
import info.imtushar.composenewsapp.presentation.home.HomeScreen
import info.imtushar.composenewsapp.presentation.home.HomeViewModel
import info.imtushar.composenewsapp.presentation.navgraph.Route
import info.imtushar.composenewsapp.presentation.news_navigator.components.NewsBottomNavigation
import info.imtushar.composenewsapp.presentation.news_navigator.components.NewsBottomNavigationItem
import info.imtushar.composenewsapp.presentation.search.SearchScreen
import info.imtushar.composenewsapp.presentation.search.SearchViewModel


@Composable
fun NewsNavigation() {

    val bottomNavigationItems = remember {
        listOf(
            NewsBottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            NewsBottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            NewsBottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when (backstackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTap(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTap(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTap(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )
                    }
                })
        }
    ) {

        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(articles = articles, navigateToSearch = {
                    navigateToTap(navController = navController, route = Route.SearchScreen.route)
                }, navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController, article = article
                    )
                })
            }

            composable(route = Route.SearchScreen.route){
                val viewModel: SearchViewModel = hiltViewModel()
                val  state = viewModel.state.value
                SearchScreen(state = state, event = viewModel::onEvent, navigateToDetails = {article->
                    navigateToDetails(navController = navController, article = article)
                })
            }

            composable(route = Route.DetailsScreen.route){
                val viewModel : DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let {article ->
                    DetailsScreen(article = article, event = viewModel::onEvent, navigateUp = {navController.navigateUp()})
                }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                //OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }

}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}