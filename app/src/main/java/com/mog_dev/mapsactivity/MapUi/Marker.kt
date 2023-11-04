package com.mog_dev.mapsactivity.MapUi

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun marker(location: LatLng, title: String = "", snippet: String = "") {
    Marker( state = MarkerState(position = location),
    title = title,
    snippet = snippet)
}