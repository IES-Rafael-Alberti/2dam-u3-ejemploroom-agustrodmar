package com.arodmar432p.taskslist.addtasks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Define una interfaz que contiene métodos para interactuar con la tabla TaskEntity en la base de datos.
 */
@Dao
interface TaskDao {
    @Query("SELECT * from TaskEntity")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(item:TaskEntity)

    @Update
    suspend fun updateTask(item:TaskEntity)

    @Delete
    suspend fun deleteTask(item:TaskEntity)
}
