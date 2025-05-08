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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.simon.proyectorutaverde.navegation.routes.Screen
import com.simon.proyectorutaverde.navegation.routes.Screen.AfMaterialesScreen
import com.simon.proyectorutaverde.navegation.routes.Screen.AfSolicitudListScreen
import com.simon.proyectorutaverde.navegation.routes.Screen.MaterialesScreen
import com.simon.proyectorutaverde.navegation.routes.Screen.ProductorChatScreen
import com.simon.proyectorutaverde.navegation.routes.Screen.SolicitudScreen
import com.simon.proyectorutaverde.screens.AfChatScreen
import com.simon.proyectorutaverde.screens.AfDetailSolicitudScreen
import com.simon.proyectorutaverde.screens.AfMaterialesScreen
import com.simon.proyectorutaverde.screens.AfSolicitudListScreen
import com.simon.proyectorutaverde.screens.MaterialesScreen
import com.simon.proyectorutaverde.screens.ModoAfiliadoScreen
import com.simon.proyectorutaverde.screens.ProductorChatScreen
import com.simon.proyectorutaverde.screens.RecicladorMainScreen
import com.simon.proyectorutaverde.screens.RecicladorProfileScreen
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
                onSolicitudClick = { navController.navigate(SolicitudScreen.route) },
                onModoAfiliadoClick = { navController.navigate(Screen.ModoAfiliadoScreen.route) },
                onUserProfClick = { navController.navigate(Screen.RecicladorProfileScreen.route) }
            )
        }

        composable(SolicitudScreen.route) {
            SolicitudScreen(
                onBackClick = { navController.navigate(Screen.RecicladorMainScreen.route) },
                onAccept = {},
                onReject = {},
                onNavigateToSolicitud = { navController.navigate(SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(ProductorChatScreen.route) }
            )
        }

        composable(MaterialesScreen.route) {
            MaterialesScreen(
                onNavigateToSolicitud = { navController.navigate(SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(ProductorChatScreen.route) }
            )
        }

        composable(Screen.RutasScreen.route) {
            RutasScreen(
                onNavigateToSolicitud = { navController.navigate(SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(ProductorChatScreen.route) }
            )
        }

        composable(ProductorChatScreen.route) {
            ProductorChatScreen(
                onNavigateToSolicitud = { navController.navigate(SolicitudScreen.route) },
                onNavigateToMateriales = { navController.navigate(MaterialesScreen.route) },
                onNavigateToRutas = { navController.navigate(Screen.RutasScreen.route) },
                onNavigateToProductorChat = { navController.navigate(ProductorChatScreen.route) }
            )
        }

        composable(Screen.ModoAfiliadoScreen.route) {
            ModoAfiliadoScreen(
                onExitClick = { navController.navigate(Screen.RecicladorMainScreen.route) },
                onRouteClick = { navController.navigate(AfSolicitudListScreen.route) },
                onUserProfClick = { navController.navigate(Screen.RecicladorProfileScreen.route) }
            )
        }

        composable(AfSolicitudListScreen.route) {
            AfSolicitudListScreen(
                currentRoute = Screen.AfSolicitudListScreen.route,
                onNavigateToSolicitud = { navController.navigate(Screen.AfSolicitudListScreen.route) },
                onNavigateToMateriales = { navController.navigate(AfMaterialesScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.AfChatScreen.route) },
                onItemClick = { solicitudId ->
                    // Usamos la función createRoute para construir la ruta con el ID
                    navController.navigate(Screen.AfDetailSolicitudScreen.createRoute(solicitudId))
                },
                onBackClick = { navController.navigate(Screen.ModoAfiliadoScreen.route) }
            )
        }

        composable (AfMaterialesScreen.route) {
            AfMaterialesScreen(
                currentRoute = AfMaterialesScreen.route,
                onNavigateToSolicitud = { navController.navigate(AfSolicitudListScreen.route) },
                onNavigateToMateriales = { navController.navigate(AfMaterialesScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.AfChatScreen.route) },
                30
            )
        }

        composable(
            route = Screen.AfDetailSolicitudScreen.route,
            arguments = listOf(navArgument("solicitudId") { type = NavType.IntType })
        ) { backStackEntry ->
            val solicitudId = backStackEntry.arguments?.getInt("solicitudId") ?: 0
            AfDetailSolicitudScreen(
                currentRoute = Screen.AfDetailSolicitudScreen.route,
                solicitudId = solicitudId,
                onBackClick = { navController.popBackStack() },
                onAccept = { /* Implementar lógica de aceptación */ },
                onReject = { /* Implementar lógica de rechazo */ },
                onNavigateToSolicitud = { navController.navigate(Screen.AfSolicitudListScreen.route) },
                onNavigateToMateriales = { navController.navigate(Screen.AfMaterialesScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.AfChatScreen.route) }
            )
        }

        composable (Screen.AfChatScreen.route){
            AfChatScreen(
                currentRoute = Screen.AfChatScreen.route,
                onNavigateToSolicitud = { navController.navigate(AfSolicitudListScreen.route) },
                onNavigateToMateriales = { navController.navigate(AfMaterialesScreen.route) },
                onNavigateToProductorChat = { navController.navigate(Screen.AfChatScreen.route)  }
            )
        }

        composable (Screen.RecicladorProfileScreen.route) {
            RecicladorProfileScreen(
                name = "Juan Pérez",
                rating = 5F,
                onEditInfo = {},
                onContactOperator = {},
                onEnterAffiliation = {},
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
