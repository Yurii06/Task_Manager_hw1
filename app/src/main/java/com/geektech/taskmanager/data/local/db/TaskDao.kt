package com.geektech.taskmanager.data.local.db

import androidx.room.*
import com.geektech.taskmanager.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

}