package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.simon.proyectorutaverde.navegation.routes.Screen


@Composable
fun AfBottomBar (
    currentRoute: String,
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToProductorChat: () -> Unit
) {
    val items = listOf(
        BottomNavItem(
            route = Screen.AfSolicitudListScreen.route,
            title = "Lista de Solicitudes",
            selectedIcon = Icons.Filled.LocationOn,
            unselectedIcon = Icons.Outlined.LocationOn
        ),
        BottomNavItem(
            route = Screen.AfMaterialesScreen.route,
            title = "Total de Materiales",
            selectedIcon = Icons.Filled.Delete,
            unselectedIcon = Icons.Outlined.Delete
        ),
        BottomNavItem(
            route = Screen.AfChatScreen.route,
            title = "Chat",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications
        )
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    when (item.route) {
                        Screen.AfSolicitudListScreen.route -> onNavigateToSolicitud()
                        Screen.AfMaterialesScreen.route -> onNavigateToMateriales()
                        Screen.AfChatScreen.route -> onNavigateToProductorChat()
                    }
                }
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun AfBottomBarPreview() {
    AfBottomBar (
        currentRoute = Screen.SolicitudScreen.route,
        onNavigateToSolicitud = {},
        onNavigateToMateriales = {},
        onNavigateToProductorChat = {}
    )
}