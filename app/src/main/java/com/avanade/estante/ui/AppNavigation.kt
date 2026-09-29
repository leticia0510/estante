package com.avanade.estante.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.avanade.estante.data.repository.LivroRepositoryImpl
import com.avanade.estante.ui.screens.LivroCadastroScreen
import com.avanade.estante.ui.screens.LivroListaScreen
import com.avanade.estante.ui.screens.LoginScreen

data class BottomNavItem<T : Any>(
    val route: T,
    val icon: ImageVector,
    val label: String
)

@Serializable
sealed class Screen{

    @Serializable
    object Login : Screen()

    @Serializable
    object LivroCadastro : Screen()

    @Serializable
    object LivroLista : Screen()

}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val buttomNavItems = listOf(
        BottomNavItem(
            Screen.LivroLista,
            Icons.AutoMirrored.Filled.MenuBook,
            "Livros"
        ),
        BottomNavItem(
            Screen.LivroCadastro,
            Icons.Default.Add,
            "Cadastrar"
        )
    )

    val showBottomBar =
        currentDestination?.hierarchy?.any {
            it.hasRoute(Screen.LivroLista::class) ||
                    it.hasRoute(Screen.LivroCadastro::class)
        } == true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigatorItem(navController, buttomNavItems)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<Screen.Login> {
                LoginScreen(navController)
            }

            composable<Screen.LivroCadastro> {
                LivroCadastroScreen(navController)
            }

            composable<Screen.LivroLista> {
                LivroListaScreen(navController)
            }

        }

    }

}

@Composable
fun BottomNavigatorItem(navController: NavController, itens: List<BottomNavItem<out Any>>){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        itens.forEach { item ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}