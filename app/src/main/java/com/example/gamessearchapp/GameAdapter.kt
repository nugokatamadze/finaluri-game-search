package com.example.gamessearchapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamessearchapp.databinding.GameItemBinding

class GameAdapter : ListAdapter<Game, GameAdapter.GameViewHolder>(GameItemCallback()) {

    var onItemClickListener: ((Game) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GameViewHolder(
        GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind()
    }

    inner class GameViewHolder(private val binding: GameItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val game = getItem(adapterPosition)
            Glide.with(binding.root)
                .load(game.image).
                placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.root)
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(game)
            }
        }
    }

    private class GameItemCallback: DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }

    }

}