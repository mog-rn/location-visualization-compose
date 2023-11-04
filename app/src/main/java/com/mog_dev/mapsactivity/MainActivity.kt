package com.mog_dev.mapsactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.mog_dev.mapsactivity.MapUi.Map
import com.mog_dev.mapsactivity.MapUi.SearchBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           Column {
               SearchBar()
               Map()

           }
        }
    }



}


