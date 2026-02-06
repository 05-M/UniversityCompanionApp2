// MapScreen.kt - Placeholder screen for the campus map feature.

package com.mido.universitycompanion.userinterface.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * [MapScreen] is a placeholder Composable for the Campus Map feature.
 * In a future iteration, this screen would integrate Google Maps or display a campus image.
 *
 * @param navController The [NavController] instance (passed for consistency, though not used here).
 */
@Composable
fun MapScreen(
    navController: NavController
) {
    // A simple column to center the placeholder text on the screen.
    Column(
        verticalArrangement = Arrangement.Center, // Vertically centers the content.
        modifier = Modifier
            .padding(16.dp) // Adds padding around the column.
            .fillMaxSize() // Ensures the column takes up the entire screen (important for centering).
    ) {
        // Placeholder text to indicate the screen's function.
        Text(
            text = "This is the Campus map screen (Feature in development)",
            fontSize = 20.sp
        )
    }
}