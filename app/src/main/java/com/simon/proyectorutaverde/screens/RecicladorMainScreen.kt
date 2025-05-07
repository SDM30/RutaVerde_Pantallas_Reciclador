package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import com.simon.proyectorutaverde.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simon.proyectorutaverde.ui.theme.ProyectoRutaVerdeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecicladorMainScreen(
    onSolicitudClick: () -> Unit,
    onModoAfiliadoClick: () -> Unit
) {
    // 1. Estado y ámbito de corrutina
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 2. Drawer con su contenido
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            RecicladorDrawerContent { destination ->
                scope.launch { drawerState.close() }
                when (destination) {
                    "afiliado" -> { onModoAfiliadoClick() }
                    "ajustes"  -> { /* Navegar a Ajustes */ }
                }
            }
        }
    ) {
        // 3. Scaffold original con botón de menú
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("# de Solicitudes: 4") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
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
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .clickable { onSolicitudClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.puntos),
                    contentDescription = "Clickable background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun RecicladorDrawerContent(onItemClick: (String) -> Unit) {
    ModalDrawerSheet {
        Text(
            text = "Menú",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        Divider()
        NavigationDrawerItem(
            label = { Text("Modo afiliado") },
            selected = false,
            onClick = { onItemClick("afiliado") },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Ajustes") },
            selected = false,
            onClick = { onItemClick("ajustes") },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecicladorMainScreenPreview() {
    ProyectoRutaVerdeTheme {
        RecicladorMainScreen(onSolicitudClick = {}, onModoAfiliadoClick = {})
    }
}



