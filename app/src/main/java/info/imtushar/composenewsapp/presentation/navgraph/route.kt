package info.imtushar.composenewsapp.presentation.navgraph

import android.media.MediaRouter.RouteCategory

sealed class Route(
    val route: String
) {

    object OnboardingScreen: Route(route = "onboardingScreen")
    object HomeScreen: Route(route = "homeScreen")
    object SearchScreen: Route(route = "searchScreen")
    object BookmarkScreen: Route(route = "bookmarkScreen")
    object DetailsScreen: Route(route = "detailsScreen")
    object AppStartNavigation: Route(route = "appStartNavigation")
    object NewsNavigation: Route(route = "newsNavigation")
    object NewsNavigationScreen: Route(route = "newsNavigationScreen")
}