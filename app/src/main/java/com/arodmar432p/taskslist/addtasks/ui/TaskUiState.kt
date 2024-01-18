package com.arodmar432p.taskslist.addtasks.ui

import com.arodmar432p.taskslist.addtasks.ui.model.TaskModel

/**
 * Estados de la pantalla
 */
sealed interface TaskUiState {
    object Loading: TaskUiState
    data class Error(val throwable: Throwable): TaskUiState
    data class Success(val tasks:List<TaskModel>) : TaskUiState
}