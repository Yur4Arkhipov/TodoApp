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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.presentation.AppViewModelProvider
import com.example.todoapp.presentation.home.TaskTopAppBar
import com.example.todoapp.presentation.navigation.NavigationDestination
import com.example.todoapp.presentation.theme.TodoAppTheme
import kotlinx.coroutines.launch


object TaskEntryDestination : NavigationDestination {
    override val route: String = "task_entry"
    override val titleRes: Int = R.string.task_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEntryScreen(
    navigateToHome: () -> Unit,
    canNavigateBack: Boolean = true,
    taskEntryViewModel: TaskEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TaskTopAppBar(
                title = stringResource(TaskEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateToHome = navigateToHome
            )
        }
    ) { innerPadding ->
        TaskEntryBody(
            taskUiState = taskEntryViewModel.taskUiState,
            onTaskValueChange = taskEntryViewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    taskEntryViewModel.saveItem()
                    navigateToHome()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun TaskEntryBody(
    taskUiState: TaskUiState,
    onTaskValueChange: (TaskDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TaskInputForm(
            taskDetails = taskUiState.taskDetails,
            onValueChange = onTaskValueChange,
        )
        Button(
            onClick = onSaveClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save")
        }
    }
}

@Composable
fun TaskInputForm(
    taskDetails:TaskDetails,
    modifier: Modifier = Modifier,
    onValueChange: (TaskDetails) -> Unit = {},
) {
    Column {
        OutlinedTextField(
            value = taskDetails.title,
            onValueChange = { onValueChange(taskDetails.copy(title = it)) },
            label = { Text("Input title") },
            modifier = modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = taskDetails.description,
            onValueChange = { onValueChange(taskDetails.copy(description = it)) },
            label = { Text("Input description") },
            modifier = modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ItemEntryScreenPreview() {
    TodoAppTheme {
        TaskEntryBody(taskUiState = TaskUiState(
            TaskDetails(
                title = "Title", description = "Description"
            )
        ), onTaskValueChange = {}, onSaveClick = {})
    }
}