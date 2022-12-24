package com.sbz.notes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sbz.notes.Models.Note
import com.sbz.notes.R
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: NotesItemClickedListener) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    private val notesList = ArrayList<Note>()
    private val fullNoteList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.note_tv.text = currentNote.note
        holder.date.text = currentNote.date
        holder.title.isSelected = true
        holder.notes_layout.setCardBackgroundColor(
            ContextCompat.getColor(
                context,
                randomColorGenerator()
            )
        )
//        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColorGenerator(), null))

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(notesList[holder.adapterPosition], holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }


    fun updateList(newList: List<Note>) {
        fullNoteList.clear()
        fullNoteList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullNoteList)
        notifyDataSetChanged()
    }

    fun filterList(search: String){
        notesList.clear()

        for(item in fullNoteList){
            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.note?.lowercase()?.contains(search.lowercase()) == true){
                notesList.add(item)

            }
        }
        notifyDataSetChanged()
    }


    fun randomColorGenerator(): Int {
        val colorList = ArrayList<Int>()

        colorList.add(R.color.NotesColor1)
        colorList.add(R.color.NotesColor2)
        colorList.add(R.color.NotesColor3)
        colorList.add(R.color.NotesColor4)
        colorList.add(R.color.NotesColor5)
        colorList.add(R.color.NotesColor6)
        colorList.add(R.color.NotesColor7)
        colorList.add(R.color.NotesColor8)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(colorList.size)
        return colorList[randomIndex]
    }


    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_time)

    }


    interface NotesItemClickedListener {

        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }


}