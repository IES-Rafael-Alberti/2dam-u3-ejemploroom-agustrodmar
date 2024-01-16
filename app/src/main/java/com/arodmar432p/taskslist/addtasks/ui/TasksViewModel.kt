package com.arodmar432p.taskslist.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arodmar432p.taskslist.addtasks.domain.AddTaskUseCase
import com.arodmar432p.taskslist.addtasks.domain.GetTasksUseCase
import com.arodmar432p.taskslist.addtasks.ui.model.TaskModel
import com.arodmar432p.taskslist.addtasks.ui.TaskUiState.Success
import com.arodmar432p.taskslist.addtasks.ui.TaskUiState.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    //Los LiveData no van bien con los listados que se tienen que ir actualizando...
    //Para solucionarlo, podemos utilizar un mutableStateListOf porque se lleva mejor
    // con LazyColumn a la hora de refrescar la información en la vista...
    // private val _tasks = mutableStateListOf<TaskModel>()
    // val tasks: List<TaskModel> = _tasks

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _myTaskText = MutableLiveData<String>()
    val myTaskText: LiveData<String> = _myTaskText

    val uiState: StateFlow<TaskUiState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUiState.Loading)

    fun onDialogClose() {
        _showDialog.value = false
    }

    //Actualizamos la función para crear la tarea en la lista anterior
    fun onTaskCreated() {
        onDialogClose()

        //Un viewModelScope es una corutina.
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = _myTaskText.value ?: ""))
        }

        _myTaskText.value = ""
    }

    fun onItemRemove() {
        //TODO: Código a eliminar. Falta desarrollar borrar tarea con un caso de uso y lanzarlo como corutina.
        //val task = _tasks.find { it.id == taskModel.id }
        //_tasks.remove(task)
    }

    fun onCheckBoxSelected() {
        //TODO: Código a eliminar. Falta desarrollar actualizar tarea con un caso de uso y lanzarlo como corutina.
        //val index = _tasks.indexOf(taskModel)
        // _tasks[index] = _tasks[index].let { it.copy(selected = !it.selected) }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTaskTextChanged(taskText: String) {
        _myTaskText.value = taskText
    }
}
