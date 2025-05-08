package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simon.proyectorutaverde.R
import com.simon.proyectorutaverde.navegation.routes.Screen
import com.simon.proyectorutaverde.ui.theme.ProyectoRutaVerdeTheme

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
                Route("Ruta #2", "37 min", "2.8 km"),
                Route("Ruta #3", "50 min", "4.2 km"),
                Route("Ruta #4", "29 min", "1.7 km")
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
        Spacer(modifier = Modifier.height(8.dp))
        // Altimetría o descripción plana
        if (route.name == "Ruta #1") {
            Image(
                painter = painterResource(id = R.drawable.ruta_altitud),
                contentDescription = "Perfil de altitud",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.FillWidth
            )
        } else {
            Text(
                text = "Principalmente llano",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 4.dp)
            )
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
