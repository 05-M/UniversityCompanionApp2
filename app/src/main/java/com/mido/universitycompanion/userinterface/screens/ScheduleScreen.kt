// ScheduleScreen.kt - Displays the user's course schedule.

package com.mido.universitycompanion.userinterface.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mido.universitycompanion.data.model.Course
import com.mido.universitycompanion.userinterface.viewmodel.ScheduleViewModel
import com.mido.universitycompanion.userinterface.viewmodel.ViewModelFactory

/**
 * [ScheduleScreen] is a Composable that displays a list of the user's courses for the week.
 * It observes the [ScheduleViewModel] to get the latest schedule data.
 *
 * @param scheduleViewModel The [ScheduleViewModel] instance, providing the list of courses.
 *                          Defaults to a ViewModel provided by [ViewModelFactory].
 * @param navController The [NavController] instance, passed for potential future navigation actions.
 */
@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = viewModel(factory = ViewModelFactory),
    navController: NavController
){
    // Collects the UI state from the ViewModel. The UI will automatically recompose
    // when this state changes.
    val scheduleUiState by scheduleViewModel.uiState.collectAsState()

    // Displays the list of courses using the ScheduleList Composable.
    ScheduleList(courses = scheduleUiState.courses)
}

/**
 * [ScheduleList] is a Composable responsible for rendering a scrollable list of courses
 * using a [LazyColumn] for efficient performance.
 *
 * @param courses The list of [Course] objects to be displayed.
 */
@Composable
fun ScheduleList(courses: List<Course>) {
    LazyColumn {
        // Iterates through the list of courses and creates a CourseCard for each one.
        items(courses){ course ->
            CourseCard(course = course)
        }
    }
}

/**
 * [CourseCard] is a Composable that displays the details of a single course
 * in a styled [Card].
 *
 * @param course The [Course] object whose details are to be displayed.
 */
@Composable
fun CourseCard(course: Course) {
    Card(
        modifier =  Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp) // Adds internal padding within the card.
        ) {
            Text(
                text = course.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                // Displays the instructor's name with a "Dr." prefix.
                text = "Dr. ${course.doctorName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row{
                // Displays the course time, taking up half of the available width.
                Text(
                    text = "Time: ${course.time}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                // Displays the course location, taking up the other half of the available width.
                Text(
                    text = "Location: ${course.location}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}