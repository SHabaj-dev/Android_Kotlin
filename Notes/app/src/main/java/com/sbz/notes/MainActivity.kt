package com.sbz.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sbz.notes.Adapter.NotesAdapter
import com.sbz.notes.Database.NoteDatabase
import com.sbz.notes.Models.Note
import com.sbz.notes.Models.NoteViewModel
import com.sbz.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NotesAdapter.NotesItemClickedListener,
    PopupMenu.OnMenuItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NoteDatabase
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NotesAdapter
    lateinit var selectedNote: Note
    private val updateNote =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if(result.resultCode == Activity.RESULT_OK){

                val note = result.data?.getSerializableExtra("note") as? Note
                if(note != null){
                    viewModel.updateNote(note)
                }
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }
        }


        database = NoteDatabase.getDatabase(this)


    }

    private fun initUI() {
        binding.rvAllNotes.setHasFixedSize(true)
        binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter = NotesAdapter(this, this)
        binding.rvAllNotes.adapter = adapter

        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val note = result.data?.getSerializableExtra("note") as? Note
                    if (note != null) {
                        viewModel.insertNote(note)
                    }
                }
            }

        binding.fbAddNewNote.setOnClickListener {
            val intent = Intent(this, AddNotes::class.java)
            getContent.launch(intent)
        }

        binding.svSearchNotes.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    adapter.filterList(newText)
                }
                return true
            }

        })
    }

    override fun onItemClicked(note: Note) {
        val intent = Intent(this@MainActivity, AddNotes::class.java)
        intent.putExtra("current_note", note)
        updateNote.launch(intent)
    }

    override fun onLongItemClicked(note: Note, cardView: CardView) {
        selectedNote = note
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {
        val popUp = PopupMenu(this, cardView)
        popUp.setOnMenuItemClickListener(this@MainActivity)
        popUp.inflate(R.menu.pop_up_menu)
        popUp.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.delete_note){
            viewModel.deleteNote(selectedNote)
            return true
        }
        return false
    }
}