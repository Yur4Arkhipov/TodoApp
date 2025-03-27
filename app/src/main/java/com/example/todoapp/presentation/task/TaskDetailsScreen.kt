package com.example.todoapp.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.presentation.AppViewModelProvider
import com.example.todoapp.presentation.home.TaskTopAppBar
import com.example.todoapp.presentation.navigation.NavigationDestination
import kotlinx.coroutines.launch


object TaskDetailsDestination : NavigationDestination {
    override val route: String = "task_details"
    override val titleRes: Int = R.string.task_details_title
    const val taskIdArg: String = "taskId"
    val routeWithArgs: String = "$route/{$taskIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    taskDetailsViewModel: TaskDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = taskDetailsViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TaskTopAppBar(
                title = stringResource(TaskDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateToHome = navigateToHome
            )
        }, modifier = modifier
    ) { innerPadding ->
        TaskDetailsBody(
            taskDetailsUiState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    taskDetailsViewModel.deleteTask()
                    navigateToHome()
                }
            },
            modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun TaskDetailsBody(
    taskDetailsUiState: TaskDetailsUiState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = taskDetailsUiState.taskDetails.title,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = taskDetailsUiState.taskDetails.description,
        )
        Button(
            onClick = onDelete,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(text = "Delete")
        }
    }
}