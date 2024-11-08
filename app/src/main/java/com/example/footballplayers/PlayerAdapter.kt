package com.example.footballplayers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballplayers.databinding.ItemPlayerBinding

class PlayerAdapter(
    private val playersList: ArrayList<Player>,
    private val onItemClicked: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player, onItemClicked: (Player) -> Unit) {
            val fullName = "${player.firstName} ${player.lastName}"
            binding.textName.text = fullName
            binding.textTeam.text = player.team

            val teamColors = TeamColors.getTeamColors(player.team)

            binding.root.setBackgroundColor(teamColors.backgroundColor)
            binding.textName.setTextColor(teamColors.textColor)
            binding.textTeam.setTextColor(teamColors.textColor)

            binding.root.apply {
                setPadding(16, 16, 16, 16)
                val params = layoutParams as RecyclerView.LayoutParams
                params.bottomMargin = 8
                layoutParams = params
            }

            binding.root.setOnClickListener {
                onItemClicked(player)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playersList[position]
        holder.bind(player, onItemClicked)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }
}