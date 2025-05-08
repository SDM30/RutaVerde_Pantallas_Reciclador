package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import com.simon.proyectorutaverde.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    onModoAfiliadoClick: () -> Unit,
    onUserProfClick: () -> Unit
) {
    // Estado para el Drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // Estado para el BottomSheet
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            RecicladorDrawerContent { destination ->
                scope.launch { drawerState.close() }
                when (destination) {
                    "afiliado" -> onModoAfiliadoClick()
                    "ajustes" -> { /* Navegar a Ajustes */ }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("# de Solicitudes: 4") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open()
                                else drawerState.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { onUserProfClick() }) {
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
                FloatingActionButton(
                    onClick = { showSheet = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar"
                    )
                }
            }
        ) { innerPadding ->
            // BottomSheet condicional
            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Tipo de residuo:", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Row(
                            Modifier.horizontalScroll(rememberScrollState())
                        ) {
                            val tipos = listOf(
                                "Papel y cartón",
                                "Plásticos",
                                "Vidrio",
                                "Metales",
                                "Textiles",
                                "Residuos electrónicos (RAEE)",
                                "Madera"
                            )
                            tipos.forEach { tipo ->
                                val isRAEE = tipo.contains("RAEE")
                                var checked by remember { mutableStateOf(false) }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(end = 12.dp)
                                ) {
                                    Checkbox(
                                        checked = checked,
                                        onCheckedChange = { checked = it }
                                    )
                                    Text(
                                        text = tipo,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = if (isRAEE) MaterialTheme.colorScheme.secondary else LocalContentColor.current
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        Text("Distancia a recorrer:", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("A menos de ")
                            var distancia by remember { mutableStateOf("") }
                            OutlinedTextField(
                                value = distancia,
                                onValueChange = { distancia = it },
                                placeholder = { Text("Km") },
                                modifier = Modifier.width(100.dp)
                            )
                            Text(" Km")
                        }

                        Spacer(Modifier.height(16.dp))
                        Text("Peso aproximado:", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Con un peso menor que ")
                            var peso by remember { mutableStateOf("") }
                            OutlinedTextField(
                                value = peso,
                                onValueChange = { peso = it },
                                placeholder = { Text("Kg") },
                                modifier = Modifier.width(100.dp)
                            )
                            Text(" Kg")
                        }
                    }
                }
            }

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
        RecicladorMainScreen(onSolicitudClick = {}, onModoAfiliadoClick = {}, onUserProfClick = {})
    }
}



