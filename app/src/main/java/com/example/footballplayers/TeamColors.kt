package com.example.footballplayers

import androidx.recyclerview.widget.RecyclerView
import com.example.footballplayers.databinding.ItemPlayerBinding
import android.graphics.Color

enum class TeamColors(val backgroundColor: Int, val textColor: Int) {
    REAL_MADRID(Color.parseColor("#FFFFFF"), Color.parseColor("#000000")),

    BARCELONA(Color.parseColor("#A50044"),Color.parseColor("#000080")),

    JUVENTUS(Color.parseColor("#000000"), Color.WHITE),

    MANCHESTER_UNITED(Color.parseColor("#DA291C"), Color.WHITE),

    FENERBAHCE(Color.parseColor("#000080"), Color.parseColor("#ffff00")),

    DEFAULT(Color.WHITE, Color.BLACK);

    companion object {
        fun getTeamColors(teamName: String): TeamColors {
            return when (teamName.trim().lowercase()) {
                "real madrid" -> REAL_MADRID
                "barcelona" -> BARCELONA
                "juventus" -> JUVENTUS
                "manchester united" -> MANCHESTER_UNITED
                "fenerbahce" -> FENERBAHCE
                else -> DEFAULT
            }
        }
    }
}

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