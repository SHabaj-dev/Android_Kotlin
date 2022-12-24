package com.sbz.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sbz.notes.Models.Note
import com.sbz.notes.databinding.ActivityAddNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNotes : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var note: Note
    private lateinit var old_note: Note
    var isUpdated = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdated = true
        }catch (e: Exception){
            e.printStackTrace()
        }

        binding.ivCheck.setOnClickListener{
            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()

            if(title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if(isUpdated){
                    note = Note(
                        old_note.id, title, note_desc, formatter.format(Date())
                    )
                }else{
                    note = Note(
                        null, title, note_desc, formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this@AddNotes, "Please Enter Some Data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

    }
}