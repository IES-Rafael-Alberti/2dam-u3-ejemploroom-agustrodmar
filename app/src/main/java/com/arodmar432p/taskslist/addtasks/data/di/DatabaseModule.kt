package com.arodmar432p.taskslist.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.arodmar432p.taskslist.addtasks.data.TaskDao
import com.arodmar432p.taskslist.addtasks.data.TasksManageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Debe ser un Singleton	para que la base de datos sea única en nuestro proyecto.
//Utilizaremos la notación de Hilt con @Provides.
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideTaskDao(tasksManageDatabase: TasksManageDatabase): TaskDao {
        return tasksManageDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTasksManageDatabase(@ApplicationContext appContext: Context): TasksManageDatabase {
        return Room.databaseBuilder(appContext, TasksManageDatabase::class.java, "TaskDatabase").build()
    }
}