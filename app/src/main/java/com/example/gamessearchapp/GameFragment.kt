package com.example.gamessearchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.gamessearchapp.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var binding: FragmentGameBinding? = null

    private val argument: GameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() = binding!!.apply {
        val game = GameUtils.gamesList[argument.selectedGamePosition]
        Glide.with(this.root).load(game.image).into(gameImageView)
        titleTextView.text = game.title
        descriptionTextView.text = game.description
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}