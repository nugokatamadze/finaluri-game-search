package com.example.gamessearchapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamessearchapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private val gameAdapter by lazy { GameAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        listeners()

    }

    private fun init() {
        binding!!.recyclerView.apply {
            adapter = gameAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
        gameAdapter.submitList(GameUtils.gamesList.toList())
    }

    private fun listeners() {
        binding!!.searchEditText.addTextChangedListener {
            gameAdapter.submitList(findElementsByTitle(it.toString()))
        }
        gameAdapter.onItemClickListener = { game ->
            SearchFragmentDirections
                .actionSearchFragmentToGameFragment(GameUtils.gamesList.indexOf(game)).also {
                    Navigation.findNavController(binding!!.root).navigate(it)
                }
        }
    }

    private fun findElementsByTitle(title: String): List<Game> {
        return GameUtils.gamesList.filter {
            it.title.lowercase().contains(title.lowercase())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}