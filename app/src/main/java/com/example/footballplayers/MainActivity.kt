package com.example.footballplayers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballplayers.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val footballPlayers = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readDataFromCsv()
        initializeRecyclerView()
    }

    private fun readDataFromCsv() {
        val inputStream = assets.open("PlayerData.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.readLine()

        var line = reader.readLine()
        while (line != null) {
            val playerData = line.split(",")

            val player = Player(
                playerId = playerData[0].toInt(),
                firstName = playerData[1],
                lastName = playerData[2],
                age = playerData[3].toInt(),
                position = playerData[4],
                nationality = playerData[5],
                height = playerData[6].toDouble(),
                weight = playerData[7].toDouble(),
                team = playerData[8],
                salary = playerData[9].toDouble()
            )
            footballPlayers.add(player)
            line = reader.readLine()
        }
        reader.close()
        inputStream.close()
    }

    private fun initializeRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        val adapter = PlayerAdapter(footballPlayers) { selectedPlayer ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("playerId", selectedPlayer.playerId)
            intent.putExtra("firstName", selectedPlayer.firstName)
            intent.putExtra("lastName", selectedPlayer.lastName)
            intent.putExtra("age", selectedPlayer.age)
            intent.putExtra("position", selectedPlayer.position)
            intent.putExtra("nationality", selectedPlayer.nationality)
            intent.putExtra("height", selectedPlayer.height)
            intent.putExtra("weight", selectedPlayer.weight)
            intent.putExtra("team", selectedPlayer.team)
            intent.putExtra("salary", selectedPlayer.salary)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
    }
}