package com.example.todoapp.ui.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R
import com.example.todoapp.navigation.NavigationDestination
import com.example.todoapp.ui.home.TaskTopAppBar


object TaskEntryDestination : NavigationDestination {
    override val route: String = "task_entry"
    override val titleRes: Int = R.string.task_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEntryScreen(

) {
    Scaffold(
        topBar = {
            TaskTopAppBar(
                title = stringResource(TaskEntryDestination.titleRes),
            )
        }
    ) { innerPadding ->
        TaskEntryBody(Modifier.padding(innerPadding))
    }
}

@Composable
fun TaskEntryBody(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TaskInputForm()
        Button(
            onClick = { TODO() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save")
        }
    }
}

@Composable
fun TaskInputForm(
    modifier: Modifier = Modifier
) {
    Column() {
        OutlinedTextField(
            value = "Title",
            onValueChange = { TODO() },
            label = { Text("Input title") },
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "Description",
            onValueChange = { TODO() },
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true
)
@Composable
fun TaskEntryBodyPreview() {
    TaskEntryScreen()
}
