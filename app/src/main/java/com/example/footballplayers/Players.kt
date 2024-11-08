package com.example.footballplayers

data class Player(
    val playerId: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val position: String,
    val nationality: String,
    val height: Double,
    val weight: Double,
    val team: String,
    val salary: Double
)