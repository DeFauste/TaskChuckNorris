package com.example.killerjoke.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import com.example.killerjoke.R
import com.example.killerjoke.data.entities.Joke
import kotlinx.android.synthetic.main.item_joke.view.*

class JokeAdapter : BaseJokeAdapter(R.layout.item_joke) {
    override var differ: AsyncListDiffer<Joke> = AsyncListDiffer(this, diffCallBack)

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokes[position]
        holder.itemView.apply {
            text_joke.text = joke.joke
        }
    }
}
