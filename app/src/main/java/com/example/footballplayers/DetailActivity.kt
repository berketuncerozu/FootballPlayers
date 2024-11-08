package com.example.footballplayers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.footballplayers.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val fullName = "${extras.getString("firstName")} ${extras.getString("lastName")}"
            binding.textName.text = fullName
            binding.textAge.text = "Age: ${extras.getInt("age")}"
            binding.textPosition.text = "Position: ${extras.getString("position")}"
            binding.textNationality.text = "Nationality: ${extras.getString("nationality")}"
            binding.textHeight.text = "Height: ${extras.getDouble("height")} cm"
            binding.textWeight.text = "Weight: ${extras.getDouble("weight")} kg"
            binding.textTeam.text = "Team: ${extras.getString("team")}"
            binding.textSalary.text = "Salary: $${String.format("%.2f", extras.getDouble("salary"))}"
        }
    }
}