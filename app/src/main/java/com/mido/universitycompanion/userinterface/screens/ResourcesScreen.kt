// ResourcesScreen.kt - Displays a list of educational resources grouped by course.

package com.mido.universitycompanion.userinterface.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mido.universitycompanion.data.model.Resource
import com.mido.universitycompanion.userinterface.viewmodel.ResourcesViewModel
import com.mido.universitycompanion.userinterface.viewmodel.ViewModelFactory

/**
 * [ResourcesScreen] is a Composable that displays a list of educational resources,
 * intelligently grouped by their corresponding course name.
 *
 * @param resourcesViewModel The [ResourcesViewModel] instance, providing the list of resources.
 *                           Defaults to a ViewModel provided by [ViewModelFactory].
 * @param navController The [NavController] instance, passed for potential future navigation actions.
 */
@Composable
fun ResourcesScreen(
    resourcesViewModel: ResourcesViewModel = viewModel(factory = ViewModelFactory),
    navController: NavController
) {
    // Collects the UI state from the ViewModel. The UI will automatically recompose
    // when this state changes.
    val resourcesUiState by resourcesViewModel.uiState.collectAsState()

    // A smart trick to group the flat list of resources by their courseName.
    // The result is a Map<String, List<Resource>>.
    val groupedResources = resourcesUiState.resources.groupBy { it.courseName }

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        // Iterates through each group (each course and its list of resources).
        groupedResources.forEach { (courseName, resources) ->
            // A sticky header could also be used here for a more advanced UI.
            item {
                // Displays the course name as a header for the group.
                Text(
                    text = courseName,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
            // Displays the list of resources for the current course.
            items(resources) { resource ->
                ResourceCard(resource = resource)
            }

        }
    }
}

/**
 * [ResourceCard] is a Composable that displays a single resource item in a clickable [Card].
 * Clicking the card opens the resource's URL in an external web browser.
 *
 * @param resource The [Resource] object to be displayed.
 */
@Composable
fun ResourceCard(resource: Resource) {
    // [LocalContext] provides access to the Android application's context,
    // which is required to start activities like opening a web browser.
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            // The clickable modifier makes the entire card interactive.
            .clickable {
                // The magic code that opens the web browser.
                // It creates an Intent with ACTION_VIEW to handle the URL.
                val intent = Intent(Intent.ACTION_VIEW, resource.url.toUri())
                context.startActivity(intent)
            }
    ) {
        // ListItem is a pre-styled Composable, perfect for displaying items in a list.
        ListItem(
            headlineContent = { Text(resource.resourceName) }, // The main text of the list item.
            leadingContent = { // Content displayed at the start of the list item.
                Icon(
                    imageVector = Icons.Filled.Link,
                    contentDescription = "Resource link icon",
                )
            }
        )
    }
}