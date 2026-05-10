package com.example.todolist.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.ui.edit.TodoEditScreen
import com.example.todolist.ui.list.TodoListScreen

@Composable
fun TodoApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(
                onAddClick = { navController.navigate("edit/0") },
                onEditClick = { id -> navController.navigate("edit/$id") },
            )
        }
        composable(
            route = "edit/{taskId}",
            arguments = listOf(
                navArgument("taskId") { type = NavType.LongType },
            ),
        ) { entry ->
            val taskId = entry.arguments!!.getLong("taskId")
            TodoEditScreen(
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}
