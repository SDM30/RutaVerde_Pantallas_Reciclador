package com.simon.proyectorutaverde.navegation.routes

sealed class Screen(val route: String) {
    object RecicladorMainScreen : Screen("RecicladorMainScreen")
    object SolicitudScreen: Screen("SolicitudScreen")
    object MaterialesScreen: Screen("MaterialesScreen")
    object RutasScreen: Screen("RutasScreen")
    object ProductorChatScreen: Screen("ProductorChatScreen")
    object ModoAfiliadoScreen: Screen("ModoAfiliadoScreen")
    object AfMaterialesScreen: Screen("AfMaterialesScreen")
    object AfSolicitudListScreen: Screen("AfSolicitudListScreen")
    object AfChatScreen: Screen("AfChatScreen")
    object RecicladorProfileScreen: Screen("RecicladorProfileScreen")

    // Definimos la ruta con el parámetro de solicitudId
    object AfDetailSolicitudScreen: Screen("AfDetailSolicitudScreen/{solicitudId}") {
        // Función para crear la ruta con un ID específico
        fun createRoute(solicitudId: Int): String {
            return "AfDetailSolicitudScreen/$solicitudId"
        }
    }
}