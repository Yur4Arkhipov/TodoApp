package com.example.todoapp.ui

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.navigation.NavigationDestination
import com.example.todoapp.ui.theme.TodoAppTheme


data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val homeUiState by homeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(HomeDestination.titleRes),
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { TODO() }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            TaskList(
                taskList = homeUiState.taskList
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
//        navigationIcon = {
//            TODO()
//        }
    )
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

@Preview(
    showSystemUi = true
)
@Composable
fun PreviewHomeScreen() {
    TodoAppTheme {
        HomeScreen()
    }
}

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