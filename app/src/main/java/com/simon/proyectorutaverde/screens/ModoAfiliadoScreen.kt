package com.simon.proyectorutaverde.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    onRouteClick: () -> Unit,
    onUserProfClick: () -> Unit
) {
    // Track current background image
    var backgroundRes by remember { mutableStateOf(R.drawable.modoafiliadodefault) }
    // Bottom sheet state
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("# de paradas: 4") },
                navigationIcon = {
                    IconButton(onClick = onExitClick) {
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "Exit"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onUserProfClick() }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "User Account"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
            ) {
                // Existing FABs
                FloatingActionButton(
                    onClick = { backgroundRes = R.drawable.agrpuntos },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Stop")
                }
                FloatingActionButton(
                    onClick = { backgroundRes = R.drawable.elimpuntos },
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Erase Stop")
                }
                // New search FAB
                FloatingActionButton(
                    onClick = { showSheet = true },
                    containerColor = MaterialTheme.colorScheme.tertiary
                ) {
                    Icon(Icons.Filled.Search, contentDescription = "Filtrar")
                }
            }
        }
    ) { innerPadding ->
        // Modal bottom sheet for filters
        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text("Tipo de residuo:", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Row(Modifier.horizontalScroll(rememberScrollState())) {
                        val tipos = listOf(
                            "Papel y cartón", "Plásticos", "Vidrio", "Metales",
                            "Textiles", "Residuos electrónicos (RAEE)", "Madera"
                        )
                        tipos.forEach { tipo ->
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
                                    color = if (tipo.contains("RAEE")) MaterialTheme.colorScheme.secondary else LocalContentColor.current
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Text("Distancia a recorrer:", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
        // Main content
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .clickable { onRouteClick() }
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
