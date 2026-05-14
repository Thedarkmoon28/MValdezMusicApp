package com.example.mvaladezmusicapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.example.mvaladezmusicapp.data.Album

@Composable
fun MiniPlayer(album: Album? = null) {
    Surface(
        color = Color(0xFF212121), // Color oscuro simple
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = album?.imageUrl ?: "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png", 
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    album?.title ?: "Select an album", 
                    color = Color.White, 
                    fontWeight = FontWeight.Bold, 
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
                Text(
                    album?.artist ?: "No artist",
                    color = Color.White.copy(alpha = 0.7f), 
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }
            
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.PlayArrow, 
                    contentDescription = "Play", 
                    tint = Color.White
                )
            }
        }
    }
}
