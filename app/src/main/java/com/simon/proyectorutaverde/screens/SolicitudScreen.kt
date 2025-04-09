package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItemDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.simon.proyectorutaverde.R
import com.simon.proyectorutaverde.navegation.routes.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitudScreen(
    onBackClick: () -> Unit = {},
    onAccept: () -> Unit = {},
    onReject: () -> Unit = {},
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToRutas: () -> Unit,
    onNavigateToProductorChat: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Solicitud de recolección") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBarSolicitud(
                currentRoute = Screen.SolicitudScreen.route,
                onNavigateToSolicitud = onNavigateToSolicitud,
                onNavigateToMateriales = onNavigateToMateriales,
                onNavigateToRutas = onNavigateToRutas,
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
            // User Info Section
            UserProfileSection()

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // Request Details Section
            Text("Detalles de la solicitud", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            RequestDetailItem("Pago estimado", "###")
            RequestDetailItem("Duración de entrega", "###")
            RequestDetailItem("Distancia promedio", "###")

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // Materials Section
            Text("Materiales disponibles", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            MaterialListItem(
                name = "Papel",
                description = "Hojas de cuaderno, impresiones",
                weight = "1.5 kg",
            )

            // Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onAccept,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Aceptar")
                }
                Spacer(Modifier.width(16.dp))
                Button(
                    onClick = onReject,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
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

@Composable
private fun MaterialListItem(
    name: String,
    description: String,
    weight: String,
    imageRes: Int = R.drawable.materiales
) {
    val imageSize = 114.dp
    val cornerRadius = 16.dp
    val itemPadding = 12.dp

    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            headlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
            supportingColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        ),
        headlineContent = {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        supportingContent = {
            Column {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
                Text(
                    text = "Peso aprox: $weight",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Imagen del material",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(cornerRadius))
                )
            }
        },
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(itemPadding)
    )
}

@Composable
private fun UserProfileSection(
    profileImage: Int = R.drawable.perfil_usuario
) {

    val imageSize = 136.dp // 136px convertido a dp (equivalente en mdpi)
    val cornerRadius = 24.dp // Radio de los bordes redondeados

    ListItem(
        headlineContent = {
            Text(
                text = "Nombre Usuario",
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Calificación",
                    style = MaterialTheme.typography.bodyMedium
                )
                StarRating(rating = 5)
                TextButton(
                    onClick = { /* Handle message action */ },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Enviar Mensaje")
                }
            }
        },
        trailingContent = {

        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Image(
                    painter = painterResource(id = profileImage),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(cornerRadius))
                )
            }
        },
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun StarRating(rating: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        repeat(rating) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SolicitudScreenPreview() {
    SolicitudScreen(
        onBackClick = {},
        onAccept = {},
        onReject = {},
        onNavigateToSolicitud = {},
        onNavigateToMateriales = {},
        onNavigateToRutas = {},
        onNavigateToProductorChat = {}
    )
}