package com.sbz.roomdblearn.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "roll_number") val roll_number: Int?,
    @ColumnInfo(name = "student_name") val student_name: String?,
    @ColumnInfo(name = "course") val course: String?
)
