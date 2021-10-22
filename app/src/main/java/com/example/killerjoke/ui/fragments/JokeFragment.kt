package com.example.killerjoke.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.killerjoke.JokeApplication
import com.example.killerjoke.R
import com.example.killerjoke.adapter.JokeAdapter
import com.example.killerjoke.data.entities.Joke
import com.example.killerjoke.databinding.FragmentJokeBinding
import com.example.killerjoke.ui.model.JokeViewModelFactory
import com.example.killerjoke.ui.model.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JokeFragment : Fragment() {
    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!
    private val adapterJoke = JokeAdapter()

    private val jokeViewModel: MainViewModel by viewModels {
        JokeViewModelFactory((requireActivity().application as JokeApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        subscribeToObservers()

        binding.reload.setOnClickListener {
            if (binding.editValue.text.toString().isNotEmpty())
                GlobalScope.launch {
                    reloadJoke()
                }
            else
                Toast.makeText(context, getString(R.string.toast_msg_edit_null), Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun reloadJoke() {
        jokeViewModel.deleteAll()
        jokeViewModel.number = binding.editValue.text.toString().toInt()
        jokeViewModel.getJokes()
    }

    private fun setupRecyclerView() = binding.recycler.apply {
        adapter = adapterJoke
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun subscribeToObservers() {
        jokeViewModel.allJokes.observe(viewLifecycleOwner) { result ->
            val list = arrayListOf<Joke>()
            result.forEach {
                list.add(Joke(it.id, it.textJoke))
            }
            adapterJoke.jokes = list
        }
    }
}
