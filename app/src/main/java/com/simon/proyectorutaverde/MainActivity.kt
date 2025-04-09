package com.simon.proyectorutaverde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simon.proyectorutaverde.navegation.routes.Screen
import com.simon.proyectorutaverde.navegation.routes.Screen.MaterialesScreen
import com.simon.proyectorutaverde.navegation.routes.Screen.ProductorChatScreen
import com.simon.proyectorutaverde.screens.MaterialesScreen
import com.simon.proyectorutaverde.screens.ProductorChatScreen
import com.simon.proyectorutaverde.screens.RecicladorMainScreen
import com.simon.proyectorutaverde.screens.RutasScreen
import com.simon.proyectorutaverde.screens.SolicitudScreen
import com.simon.proyectorutaverde.ui.theme.ProyectoRutaVerdeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoRutaVerdeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost (
        navController = navController,
        startDestination = Screen.RecicladorMainScreen.route
    ) {
        composable(Screen.RecicladorMainScreen.route) {
            RecicladorMainScreen(
                onSolicitudClick = { navController.navigate(Screen.SolicitudScreen.route) }
            )
        }

        composable(Screen.SolicitudScreen.route) {
            SolicitudScreen(
                onBackClick = { Screen.RecicladorMainScreen.route },
                onAccept = {},
                onReject = {},
                onNavigateToSolicitud = { navController.navigate(Screen.SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(Screen.MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.ProductorChatScreen.route) }
            )
        }

        composable(Screen.MaterialesScreen.route) {
            MaterialesScreen(
                onNavigateToSolicitud = { navController.navigate(Screen.SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(Screen.MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.ProductorChatScreen.route) }
            )
        }

        composable(Screen.RutasScreen.route) {
            RutasScreen(
                onNavigateToSolicitud = { navController.navigate(Screen.SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(Screen.MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.ProductorChatScreen.route) }
            )
        }

        composable(Screen.ProductorChatScreen.route) {
            ProductorChatScreen(
                onNavigateToSolicitud = { navController.navigate(Screen.SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(Screen.MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.ProductorChatScreen.route) }
            )
        }

    }
}
