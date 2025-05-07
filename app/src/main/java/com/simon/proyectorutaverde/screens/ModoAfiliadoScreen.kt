package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.simon.proyectorutaverde.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModoAfiliadoScreen(
    onExitClick: () -> Unit,
    onRouteClick: () -> Unit
) {
    // Track current background image
    var backgroundRes by remember { mutableStateOf(R.drawable.modoafiliadodefault) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("# de paradas: 4") },
                navigationIcon = {
                    IconButton(onClick = onExitClick) {
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "Exit",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Cuenta de usuario */ }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "User Account",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },

        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        backgroundRes = R.drawable.agrpuntos
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Route")
                }

                FloatingActionButton(
                    onClick = {
                        backgroundRes = R.drawable.elimpuntos
                    },
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Erase Stop")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .clickable {
                    onRouteClick()
                }
        ) {
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
