package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.simon.proyectorutaverde.navegation.routes.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AfSolicitudListScreen(
    currentRoute: String,
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToProductorChat: () -> Unit,
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ruta de Solicitudes") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            AfBottomBar(
                currentRoute = currentRoute,
                onNavigateToSolicitud = onNavigateToSolicitud,
                onNavigateToMateriales = onNavigateToMateriales,
                onNavigateToProductorChat = onNavigateToProductorChat
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(List(10) { it + 1 }) { index ->
                SolicitudListItem(
                    solicitudNumber = index,
                    onClick = { onItemClick(index) }
                )
                Divider()
            }
        }
    }
}

@Composable
private fun SolicitudListItem(
    solicitudNumber: Int,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = "Solicitud #$solicitudNumber",
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                text = "Detalles de la solicitud $solicitudNumber",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingContent = {
            Icon(
                imageVector = Icons.Filled.ContactPage,
                contentDescription = "Ícono de solicitud",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp)
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Filled.NavigateNext,
                contentDescription = "Ir a detalles",
                modifier = Modifier.padding(8.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 4.dp)
    )
}