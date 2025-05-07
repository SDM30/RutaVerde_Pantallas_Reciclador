package com.simon.proyectorutaverde.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.simon.proyectorutaverde.R
import com.simon.proyectorutaverde.navegation.routes.Screen

@Composable
fun AfChatScreen(
    currentRoute: String,
    onNavigateToSolicitud: () -> Unit,
    onNavigateToMateriales: () -> Unit,
    onNavigateToProductorChat: () -> Unit
) {
    Scaffold(
        bottomBar = {
            AfBottomBar(
                currentRoute = currentRoute,
                onNavigateToSolicitud = onNavigateToSolicitud,
                onNavigateToMateriales = onNavigateToMateriales,
                onNavigateToProductorChat = onNavigateToProductorChat
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = "Chat background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
