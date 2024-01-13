package com.arodmar432p.taskslist.addtasks.domain

import com.arodmar432p.taskslist.addtasks.data.TaskRepository
import com.arodmar432p.taskslist.addtasks.ui.model.TaskModel
import javax.inject.Inject

/**
 * Caso de uso para añadir una tarea
 */
class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.add(taskModel)
    }
}