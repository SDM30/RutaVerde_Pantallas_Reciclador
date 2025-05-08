package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simon.proyectorutaverde.navegation.routes.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AfDetailSolicitudScreen(
    currentRoute: String,
    solicitudId: Int,
    onBackClick: () -> Unit = {},
    onAccept: () -> Unit = {},
    onReject: () -> Unit = {},
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToProductorChat: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Solicitud #$solicitudId") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // — Header with icon in place of profile image —
            ListItem(
                headlineContent = {
                    Text("Nombre Usuario", style = MaterialTheme.typography.titleMedium)
                },
                supportingContent = {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        // Calificación con estrellas
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        // Botón de mensaje debajo de la calificación
                        TextButton(
                            onClick = { /* mensaje */ },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text("Enviar Mensaje")
                        }
                    }
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Usuario",
                        modifier = Modifier.size(56.dp)
                    )
                },
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Divider(modifier = Modifier.padding(vertical = 12.dp))

            // — Request Details —
            Text("Detalles de la solicitud", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            RequestDetailItem("ID de Solicitud", "#$solicitudId")
            RequestDetailItem("Duración de entrega", "30 minutos")
            RequestDetailItem("Distancia promedio", "3.5 km")

            // Instrucciones de recogida
            Spacer(Modifier.height(12.dp))
            Text("Instrucciones de recogida", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text(
                    "Timbrar al 201",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // — Action Buttons —
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onAccept,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Aceptar")
                }
                Button(
                    onClick = onReject,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Rechazar")
                }
            }
        }
    }
}

@Composable
private fun RequestDetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}