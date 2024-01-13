package com.arodmar432p.taskslist.addtasks.ui.model

//Our data model...
//El valor del id por defecto lo vamos a calcular con el momento en el que lo creamos, es decir, el tiempo en milisegundos.
//El valor de la casilla de verificación por defecto debería ser siempre falso cuando creamos la tarea.
data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
)