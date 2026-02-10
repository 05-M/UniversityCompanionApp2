// ResourcesScreen.kt - Displays a list of educational resources grouped by course.

package com.mido.universitycompanion.userinterface.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mido.universitycompanion.data.model.Resource
import com.mido.universitycompanion.userinterface.viewmodel.DataState
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(
    resourcesViewModel: ResourcesViewModel = viewModel(factory = ViewModelFactory),
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Course Resources") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        val resourcesUiState by resourcesViewModel.uiState.collectAsState()
        when (val state = resourcesUiState.resourceState) {
            is DataState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is DataState.Success<*> -> {
                // هنا الحل: بنمرر الليستة كلها لـ Composable جديد اسمه ResourceList
                ResourceList(resources = state.data as List<Resource>, modifier = Modifier.padding(innerPadding))
            }

            is DataState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ErrorScreen(
                        errorMessage = state.message,
                        onRetry = { resourcesViewModel.getResources() } // <-- هنا بننادي على Retry
                    )
                }
            }
        }
    }
}

// الكومبوزابل الجديد اللي هيعرض الليستة
@Composable
fun ResourceList(resources: List<Resource>, modifier: Modifier = Modifier) {
    val groupedResources = resources.groupBy { it.courseName }

    // هنا الـ LazyColumn هياخد modifier مختلف
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp) // ده بيدي padding لكل المحتوى اللي جوه الليستة
    ) {
        // بنعمل تكرار على كل مجموعة
        groupedResources.forEach { (courseName, courseResources) ->
            // بنحط Card لكل مجموعة
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp) // مسافة بين الكاردات
                ) {
                    Column {
                        // عنوان الكورس جوه الكارد
                        Text(
                            text = courseName,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(16.dp)
                        )
                        // بنعرض المصادر بتاعت الكورس ده
                        courseResources.forEach { resource ->
                            ResourceCard(resource = resource)
                        }
                    }
                }
            }
        }
    }
}

// هنعدل الـ ResourceCard عشان يبقى شكله أنضف جوه الـ Card
@Composable
fun ResourceCard(resource: Resource) {
    val context = LocalContext.current

    // مبقناش محتاجين Card هنا، هنستخدم ListItem بس
    ListItem(
        headlineContent = { Text(resource.resourceName) },
        leadingContent = {
            Icon(
                Icons.Filled.Link,
                contentDescription = "Resource link icon",
            )
        },
        modifier = Modifier.clickable { // بنخلي الـ ListItem هو اللي clickable
            val intent = Intent(Intent.ACTION_VIEW, resource.url.toUri())
            context.startActivity(intent)
        }
    )
}