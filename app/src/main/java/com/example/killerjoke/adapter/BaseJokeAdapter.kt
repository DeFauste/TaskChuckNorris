package com.example.killerjoke.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.killerjoke.data.entities.Joke

abstract class BaseJokeAdapter(private val layoutId: Int) :
    RecyclerView.Adapter<BaseJokeAdapter.JokeViewHolder>() {

    class JokeViewHolder(item: View) : RecyclerView.ViewHolder(item)

    protected val diffCallBack = object : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    protected abstract var differ: AsyncListDiffer<Joke>

    var jokes: List<Joke>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                layoutId,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return jokes.size
    }
}
