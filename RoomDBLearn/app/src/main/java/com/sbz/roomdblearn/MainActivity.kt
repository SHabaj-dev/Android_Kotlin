package com.sbz.roomdblearn


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sbz.roomdblearn.Database.AppDatabase
import com.sbz.roomdblearn.Database.Student
import com.sbz.roomdblearn.Database.StudentDAO
import com.sbz.roomdblearn.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDB: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDB = AppDatabase.getDatabase(this)

        binding.btnSave.setOnClickListener {
            writeData()
        }

        binding.btnSearch.setOnClickListener {
            readData()
        }

        binding.btnDelete.setOnClickListener {
            deleteData()
        }
    }


    private fun writeData() {
        val rollNumber = binding.etRollNo.text.toString()
        val studentName = binding.etStudentName.text.toString()
        val branch = binding.etBranch.text.toString()

        if (rollNumber.isNotEmpty() && studentName.isNotEmpty() && branch.isNotEmpty()) {
            val student = Student(
                null, rollNumber.toInt(), studentName, branch
            )

            GlobalScope.launch(Dispatchers.IO) {
                appDB.studentDao().insert(student)
            }
            Toast.makeText(
                this@MainActivity,
                "Saved Successfully",
                Toast.LENGTH_SHORT
            ).show()

            binding.etRollNo.text.clear()
            binding.etStudentName.text.clear()
            binding.etBranch.text.clear()
            binding.tvDisplayData.visibility = View.GONE

        } else {
            Toast.makeText(
                this@MainActivity,
                "Please Enter Credentials CareFully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun readData() {
        val searchData = binding.etSearchText.text.toString()

        if (searchData.isNotEmpty()) {
            lateinit var student: Student

            GlobalScope.launch {
                student = appDB.studentDao().findByRollNo(searchData.toInt())
                displayData(student)
            }
            binding.etSearchText.text.clear()


        } else {
            Toast.makeText(
                this@MainActivity,
                "Search Field can't be empty!!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private suspend fun displayData(student: Student) {
        val outputData = "${student.roll_number}  ${student.student_name}  ${student.course}"
        withContext(Dispatchers.Main) {
            binding.tvDisplayData.text = outputData
        }
        binding.tvDisplayData.visibility = View.VISIBLE
    }

    private fun deleteData() {
        val rollNum = binding.etSearchText.text.toString()
        if (rollNum.isNotEmpty()) {

            GlobalScope.launch {
                appDB.studentDao().deleteRollNo(rollNum.toInt())
            }
            Toast.makeText(
                this@MainActivity,
                "${rollNum} deletion successful",
                Toast.LENGTH_SHORT
            ).show()

            binding.etSearchText.text.clear()
        }
    }

}