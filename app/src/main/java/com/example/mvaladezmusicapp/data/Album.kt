package com.example.mvaladezmusicapp.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String,
    @SerialName("image")
    val imageUrl: String
)
