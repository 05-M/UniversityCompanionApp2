// MainActivity.kt - The primary entry point and navigation host for the application.

package com.mido.universitycompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mido.universitycompanion.ui.theme.UniversityCompanionTheme
import com.mido.universitycompanion.userinterface.screens.HomeScreen
import com.mido.universitycompanion.userinterface.screens.MapScreen
import com.mido.universitycompanion.userinterface.screens.ResourcesScreen
import com.mido.universitycompanion.userinterface.screens.ScheduleScreen

/**
 * [MainActivity] serves as the main entry point for the University Companion application.
 * It sets up the fundamental UI structure, including the theme and the navigation graph.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is first created.
     * This function initializes the UI, enables edge-to-edge display, and sets up the NavHost.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enables "Edge-to-Edge" mode, allowing the UI to extend across the entire screen, including system bars.
        enableEdgeToEdge()
        // Sets the content of the activity's user interface using Jetpack Compose.
        setContent {
            // Applies the custom theme for the application, which supports automatic light/dark mode.
            UniversityCompanionTheme {
                // Scaffold provides a basic visual structure. Here, it ensures proper layout behavior
                // for components like the system bars when using edge-to-edge display.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Creates and remembers a NavController to manage navigation between app screens.
                    val navController = rememberNavController()
                    // [NavHost] is the container that displays different screens (Composables) based on the current navigation route.
                    NavHost(
                        navController = navController, // Links NavHost with NavController for navigation management.
                        startDestination = "home", // Defines the default screen to display on app startup.
                        modifier = Modifier.padding(innerPadding) // Applies padding from Scaffold to prevent content from overlapping with system bars.
                    ){
                        // Defines the "home" route, which displays the HomeScreen Composable.
                        composable("home") {
                            HomeScreen(navController = navController) // Passes NavController to enable navigation from this screen.
                        }
                        // Defines the "schedule" route for the ScheduleScreen.
                        composable("schedule") {
                            ScheduleScreen(navController = navController) // Passes NavController for potential back navigation or other actions.
                        }

                        // Defines the "resources" route for the ResourcesScreen.
                        composable("resources") {
                            ResourcesScreen(navController = navController)
                        }

                        // Defines the "map" route for the MapScreen.
                        composable("map") {
                            MapScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}