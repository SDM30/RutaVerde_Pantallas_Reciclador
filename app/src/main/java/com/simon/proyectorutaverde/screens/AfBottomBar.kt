package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simon.proyectorutaverde.navegation.routes.Screen



@Composable
fun AfBottomBar(
    currentRoute: String,
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToProductorChat: () -> Unit
) {
    val items = listOf(
        BottomNavItem(
            route = Screen.AfSolicitudListScreen.route,
            title = "Solicitudes",
            selectedIcon = Icons.Filled.LocationOn,
            unselectedIcon = Icons.Outlined.LocationOn
        ),
        BottomNavItem(
            route = Screen.AfMaterialesScreen.route,
            title = "Materiales",
            selectedIcon = Icons.Filled.Delete,
            unselectedIcon = Icons.Outlined.Delete
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
                label = {
                    Text(
                        text = item.title,
                        textAlign = TextAlign.Center,  // Centra el texto horizontalmente
                        modifier = Modifier.padding(bottom = 2.dp)  // Añade un pequeño padding inferior
                    )
                },
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
        currentRoute = Screen.AfSolicitudListScreen.route,
        onNavigateToSolicitud = {},
        onNavigateToMateriales = {},
        onNavigateToProductorChat = {}
    )
}