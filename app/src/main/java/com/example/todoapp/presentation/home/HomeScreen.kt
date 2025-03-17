package com.example.todoapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.Task
import com.example.todoapp.presentation.navigation.NavigationDestination
import com.example.todoapp.presentation.theme.TodoAppTheme

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToTaskEntry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val homeUiState by homeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TaskTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToTaskEntry() }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        HomeBody(
            taskList = homeUiState.tasks,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateToHome: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateToHome) {
                    Icon(
                        imageVector =Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun HomeBody(
    taskList: List<Task>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if (taskList.isEmpty()) {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = "No tasks found")
            }
        } else {
            TaskList(taskList)
        }
    }
}


@Composable
fun TaskList(
    taskList: List<Task>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(taskList) { task ->
            TaskItem(task)
        }
    }
}

@Composable
private fun TaskItem(
    item: Task,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Icon(
                painter = if (item.isCompleted) {
                    painterResource(R.drawable.task_complete)
                } else {
                    painterResource(R.drawable.task_not_complete)
                },
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .size(40.dp)
            )
            Column(
                modifier = Modifier.weight(6f)
            ) {
                Text(
                    text = item.title
                )
                Text(
                    text = item.description,
                )
            }
        }
    }
}

/*@Preview(
    showSystemUi = true
)
@Composable
fun PreviewHomeScreen() {
    TodoAppTheme {
        HomeScreen()
    }
}*/

/*@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    MVVMTodoAppTheme {
        HomeBody(listOf(), onItemClick = {})
    }
}*/

@Preview
@Composable
fun PreviewTodoItem() {
    TodoAppTheme {
        TaskItem(Task(1, "Homework", "Do math homework", true))
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    TodoAppTheme() {
        TaskList(
            listOf(
                Task(1, "Homework", "Do math homework", false),
                Task(2, "Game", "Play basketball", false),
                Task(3, "TV", "Watch about frog", true)
            ),
//            onItemClick = {}
        )
    }
}