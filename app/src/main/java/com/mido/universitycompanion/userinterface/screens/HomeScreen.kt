// HomeScreen.kt - The main hub screen of the application, providing navigation to different features.

package com.mido.universitycompanion.userinterface.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * [HomeScreen] is the central navigation hub for the application.
 * It displays interactive cards for each main feature (Schedule, Resources, Campus Map)
 * and navigates the user to the corresponding screen upon interaction.
 *
 * @param navController The [NavController] instance to manage navigation to other screens.
 */
@OptIn(ExperimentalMaterial3Api::class) // Enables the use of experimental Material 3 APIs like TopAppBar.
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            // Displays a TopAppBar at the top of the screen with the application's title.
            TopAppBar(
                title = { Text("University Companion") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        // The main column that centers the service icons on the screen.
        Column(
            modifier = Modifier
                .fillMaxSize() // Fills the entire available screen space.
                .padding(innerPadding) // Applies padding from Scaffold to avoid overlapping with system bars.
                .padding(16.dp), // Adds internal padding for the content.
            horizontalAlignment = Alignment.CenterHorizontally, // Centers content horizontally.
            verticalArrangement = Arrangement.Center // Centers content vertically, creating the hub layout.
        ) {
            // Displays the interactive service icons.
            ServiceIcon(
                icon = Icons.Filled.DateRange, // Icon for the schedule.
                label = "Schedule",
                onClick = { navController.navigate("schedule") } // Navigates to the schedule screen.
            )
            Spacer(modifier = Modifier.height(32.dp)) // Provides vertical spacing between icons.
            ServiceIcon(
                icon = Icons.Filled.Info, // Icon for resources.
                label = "Resources",
                onClick = { navController.navigate("resources") } // Navigates to the resources screen.
            )
            Spacer(modifier = Modifier.height(32.dp))
            ServiceIcon(
                icon = Icons.Filled.LocationOn, // Icon for the campus map.
                label = "Campus Map",
                onClick = { navController.navigate("map") } // Navigates to the map screen.
            )
        }
    }
}

/**
 * A reusable Composable that displays a large, clickable card with an icon and a label.
 * It serves as a navigation entry point for a specific feature.
 *
 * @param icon The [ImageVector] to be displayed in the card.
 * @param label The text label to be displayed below the icon.
 * @param onClick The lambda function to be invoked when the card is clicked.
 */
@Composable
fun ServiceIcon(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(150.dp) // Sets a fixed size for the card.
            .clickable(onClick = onClick) // Makes the entire card clickable.
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label, // For accessibility.
                modifier = Modifier.size(60.dp), // Sets the size of the icon.
                tint = MaterialTheme.colorScheme.primary // Uses the theme's primary color for the icon.
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, style = MaterialTheme.typography.titleMedium)
        }
    }
}