package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.simon.proyectorutaverde.R
import com.simon.proyectorutaverde.navegation.routes.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AfMaterialesScreen(
    currentRoute: String,
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToProductorChat: () -> Unit,
    materialCount: Int = 10
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Peso estimado: ${materialCount * 0.5} kg",
                            style = MaterialTheme.typography.titleMedium
                        )
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 8.dp)
        ) {
            items(materialCount) { index ->
                MaterialItem(
                    number = index + 1,
                    title = when (index % 5) {
                        0 -> "Papel reciclado"
                        1 -> "Plástico PET"
                        2 -> "Vidrio transparente"
                        3 -> "Metal aluminio"
                        else -> "Desechos orgánicos"
                    },
                    description = when (index % 5) {
                        0 -> "Hojas de oficina, periódicos"
                        1 -> "Botellas y envases plásticos"
                        2 -> "Botellas y frascos de vidrio"
                        3 -> "Latas y piezas metálicas"
                        else -> "Restos vegetales y alimentos"
                    },
                    weight = "${1.5 + (index * 0.3)} kg",
                    imageRes = when (index % 5) {
                        0 -> R.drawable.material1
                        1 -> R.drawable.material2
                        2 -> R.drawable.material3
                        3 -> R.drawable.material4
                        else -> R.drawable.material5
                    }
                )
            }
        }
    }
}

@Composable
private fun MaterialItem(
    number: Int,
    title: String,
    description: String,
    weight: String,
    imageRes: Int
) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        headlineContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
        },
        supportingContent = {
            Column {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Peso: $weight",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = "Material image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "#$number",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(4.dp)
                )
            }
        },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    )
}
