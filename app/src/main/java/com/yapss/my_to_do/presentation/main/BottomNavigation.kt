package com.yapss.my_to_do.presentation.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yapss.my_to_do.data.model.sealed.Routes
import com.yapss.my_to_do.presentation.calendar.CalendarScreen
import com.yapss.my_to_do.presentation.profile.ProfileScreen
import com.yapss.my_to_do.presentation.tags.TagsScreen
import com.yapss.my_to_do.presentation.todo.ToDoScreen

@Composable
fun BottomNavigationPart(navController: NavHostController){
    val routes = listOf(Routes.ToDo, Routes.Calendar, Routes.Tags, Routes.Profile)
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    BottomAppBar (
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        windowInsets = WindowInsets(0.dp),
        contentPadding = PaddingValues(0.dp)
    ){
        Column {
            HorizontalDivider()
            Row {
                routes.forEach{ route->
                    NavigationBarItem(
                        selected = currentRoute == route.route,
                        icon = { Icon(painter = painterResource(if(currentRoute == route.route) route.iconActive else route.iconPassive), contentDescription = route.label) },
                        label = { Text(route.label) },
                        alwaysShowLabel = true,
                        onClick = {
                            navController.navigate(route.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationHostPart(navController:NavHostController,startDestination:String = Routes.ToDo.route, modifier: Modifier = Modifier){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) }
    ){
        composable(Routes.ToDo.route){
            ToDoScreen(modifier = modifier)
        }
        composable(Routes.Calendar.route){
            CalendarScreen(modifier = modifier)
        }
        composable(Routes.Tags.route){
            TagsScreen(modifier = modifier)
        }
        composable(Routes.Profile.route){
            ProfileScreen(modifier = modifier)
        }
    }
}