package com.kshitizbali.nycschools.domain.util

/**
 * Screen helper class for composable navigation.
 */
sealed class Screen(val route: String){
    object SchoolsListScreen : Screen("schools_list_screen")
    object SchoolDetailScreen: Screen("school_detail_screen")

    fun withArgs(vararg args: String?): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}