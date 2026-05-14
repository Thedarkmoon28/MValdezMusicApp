package com.example.mvaladezmusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mvaladezmusicapp.ui.MusicNavGraph
import com.example.mvaladezmusicapp.ui.theme.MVALADEZMUSICAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVALADEZMUSICAPPTheme {
                MusicNavGraph()
            }
        }
    }
}
