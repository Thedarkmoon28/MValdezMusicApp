package com.example.mvaladezmusicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mvaladezmusicapp.data.Album
import com.example.mvaladezmusicapp.network.MusicApi
import com.example.mvaladezmusicapp.ui.MiniPlayer

@Composable
fun HomeScreen(onAlbumClick: (String) -> Unit) {
    var albums by remember { mutableStateOf<List<Album>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            albums = MusicApi.retrofitService.getAlbums()
        } catch (e: Exception) {
            // Manejar error
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        bottomBar = { MiniPlayer() }
    ) { innerPadding ->
        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF6200EE).copy(alpha = 0.2f), Color.Transparent)
                                )
                            )
                    ) {
                        HeaderSection()
                    }
                }
                item {
                    Text(
                        "Albums",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    AlbumsRow(albums = albums, onAlbumClick = onAlbumClick)
                }
                item {
                    Text(
                        "Recently Played",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                items(albums.reversed()) { album ->
                    RecentlyPlayedItem(album = album, onAlbumClick = onAlbumClick)
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hello,", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        Text(
            "Mario Valadez", 
            style = MaterialTheme.typography.headlineMedium, 
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun AlbumsRow(albums: List<Album>, onAlbumClick: (String) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(albums) { album ->
            AlbumCard(album = album, onClick = { onAlbumClick(album.id) })
        }
    }
}

@Composable
fun AlbumCard(album: Album, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            AsyncImage(
                model = album.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            // Play Button
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(36.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.8f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.PlayArrow, 
                        contentDescription = null, 
                        tint = Color.Black
                    )
                }
            }
        }
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                album.title, 
                fontWeight = FontWeight.Bold, 
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                album.artist, 
                style = MaterialTheme.typography.bodySmall, 
                color = Color.Gray
            )
        }
    }
}

@Composable
fun RecentlyPlayedItem(album: Album, onAlbumClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onAlbumClick(album.id) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = album.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    album.title, 
                    fontWeight = FontWeight.Bold
                )
                Text(
                    album.artist, 
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

// MiniPlayer was removed and moved to ui.Components.kt
