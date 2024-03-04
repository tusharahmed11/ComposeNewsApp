package info.imtushar.composenewsapp.presentation.navgraph

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import info.imtushar.composenewsapp.presentation.news_navigator.NewsNavigation
import info.imtushar.composenewsapp.presentation.onboarding.OnboardingScreen
import info.imtushar.composenewsapp.presentation.onboarding.OnboardingViewModel

@Composable
fun NavGraph(
    startDestination: String
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route
        ){
            composable(
                route = Route.OnboardingScreen.route
            ){

                val viewModel: OnboardingViewModel = hiltViewModel()
                
                OnboardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ){
            composable(
                route = Route.NewsNavigationScreen.route
            ){
           /*     val viewModel : HomeViewModel = hiltViewModel()

                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(articles = articles, navigate = {})*/

              //  val viewModel: SearchViewModel = hiltViewModel()
/*                val viewModel: BookmarkViewModel = hiltViewModel()

                BookmarkScreen(state = viewModel.state.value,  navigateToDetails = {})*/

                NewsNavigation()

            }
        }
    }
}