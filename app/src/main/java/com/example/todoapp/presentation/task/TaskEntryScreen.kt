package com.example.todoapp.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.todoapp.R
import com.example.todoapp.presentation.navigation.NavigationDestination
import com.example.todoapp.presentation.home.TaskTopAppBar


object TaskEntryDestination : NavigationDestination {
    override val route: String = "task_entry"
    override val titleRes: Int = R.string.task_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEntryScreen(
    taskEntryViewModel: TaskEntryViewModel,
    navigateToHome: () -> Unit,
) {
    Scaffold(
        topBar = {
            TaskTopAppBar(
                title = stringResource(TaskEntryDestination.titleRes),
                canNavigateBack = true,
                navigateToHome = navigateToHome
            )
        }
    ) { innerPadding ->
        TaskEntryBody(
            taskEntryViewModel = taskEntryViewModel,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun TaskEntryBody(
    taskEntryViewModel: TaskEntryViewModel,
    modifier: Modifier = Modifier,
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        TaskInputForm(
            title = title,
            onTitleChange = { title = it},
            description = description,
            onDescriptionChange = { description = it }
        )
        Button(
            onClick = { taskEntryViewModel.addTask(
                title = title,
                description = description
            ) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save")
        }
    }
}

@Composable
fun TaskInputForm(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column() {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("Input title") },
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text("Input description") },
            modifier = modifier.fillMaxWidth()
        )
    }
}

/*@Preview
@Composable
fun TaskInputFormPreview() {
    TaskInputForm()
}*/

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(
//    showSystemUi = true
//)
//@Composable
//fun TaskEntryBodyPreview() {
//    TaskEntryScreen()
//}
