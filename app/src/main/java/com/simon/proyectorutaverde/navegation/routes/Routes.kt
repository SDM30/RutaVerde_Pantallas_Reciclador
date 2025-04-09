package com.simon.proyectorutaverde.navegation.routes

sealed class Screen(val route: String) {
    object RecicladorMainScreen : Screen("RecicladorMainScreen")
    object SolicitudScreen: Screen("SolicitudScreen")
    object MaterialesScreen: Screen("MaterialesScreen")
    object RutasScreen: Screen("RutasScreen")
    object ProductorChatScreen: Screen("ProductorChatScreen")
}