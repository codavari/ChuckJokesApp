package ru.slavicsky.chuckjokesapp.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.joke_item.view.*
import ru.slavicsky.chuckjokesapp.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var jokeText: TextView? = itemView.findViewById(R.id.joke_text)
}