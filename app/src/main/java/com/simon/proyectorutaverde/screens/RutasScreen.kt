package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.simon.proyectorutaverde.R
import com.simon.proyectorutaverde.navegation.routes.Screen
import com.simon.proyectorutaverde.ui.theme.ProyectoRutaVerdeTheme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.FloatingActionButton



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutasScreen(
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToRutas: () -> Unit,
    onNavigateToProductorChat: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            RouteDetailsSheet(routes = listOf(
                Route("Ruta #1", "43 min", "3.1 km"),
                Route("Ruta #2", "43 min", "3.2 km"),
                Route("Ruta #3", "43 min", "3.2 km"),
                Route("Ruta #4", "43 min", "3.2 km")
            ))
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBarSolicitud(
                currentRoute = Screen.RutasScreen.route,
                onNavigateToSolicitud = onNavigateToSolicitud,
                onNavigateToMateriales = onNavigateToMateriales,
                onNavigateToRutas = onNavigateToRutas,
                onNavigateToProductorChat = onNavigateToProductorChat
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSheet = !showSheet },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 72.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Route,
                    contentDescription = "Mostrar rutas",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.rutas),
                contentDescription = "Mapa de rutas",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun RouteDetailsSheet(routes: List<Route>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Rutas Disponibles",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(routes) { route ->
                RouteItem(route = route)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
fun RouteItem(route: Route) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = route.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tiempo: ${route.time}", style = MaterialTheme.typography.bodyMedium)
            Text("Distancia: ${route.distance}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

data class Route(
    val name: String,
    val time: String,
    val distance: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RutasScreenPreview() {
    ProyectoRutaVerdeTheme {
        RutasScreen(
            onNavigateToSolicitud = {},
            onNavigateToMateriales = {},
            onNavigateToRutas = {},
            onNavigateToProductorChat = {}
        )
    }
}