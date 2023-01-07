package com.sbz.roomdblearn.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface StudentDAO {

    //To get All the student info from Database
    @Query("SELECT * FROM student_table")
    fun getAll(): List<Student>

    //To get Specific Student from the Database
    @Query("SELECT * FROM student_table WHERE roll_number = :roll_num")
    suspend fun findByRollNo(roll_num: Int) : Student

    //To insert the data into the Database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)

    //delete the Matching student info from the table
    @Delete
    suspend fun delete(student: Student)

    //delete all the data inside the table
    @Query("DELETE FROM student_table")
    suspend fun deleteAll()

    @Query("DELETE FROM student_table WHERE roll_number = :roll_num")
    suspend fun deleteRollNo(roll_num: Int)
}