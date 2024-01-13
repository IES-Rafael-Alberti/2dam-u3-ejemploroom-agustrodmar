package com.arodmar432p.taskslist.addtasks.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(false)
    val myTaskText: String by tasksViewModel.myTaskText.observeAsState("")

    Box(modifier = Modifier.fillMaxSize()) {
        AddTasksDialog(
            show = showDialog,
            myTaskText = myTaskText,
            onDismiss = { tasksViewModel.onDialogClose() },
            onTaskAdded = { tasksViewModel.onTaskCreated() },
            onTaskTextChanged = { tasksViewModel.onTaskTextChanged(it) }
        )
        FabDialog(
            Modifier.align(Alignment.BottomEnd),
            onNewTask = { tasksViewModel.onShowDialogClick() })
        TasksList(tasksViewModel)
    }
}

@Composable
fun FabDialog(
    modifier: Modifier
) {

    FloatingActionButton(
        onClick = {
            // Show dialog to add a task
        }, modifier = modifier.padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTasksScreen() {

}