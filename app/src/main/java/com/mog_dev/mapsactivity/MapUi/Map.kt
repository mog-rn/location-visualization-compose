package com.mog_dev.mapsactivity.MapUi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.mog_dev.mapsactivity.MapData.locationLiveData
import com.mog_dev.mapsactivity.R

@Composable
fun Map() {
    val context = LocalContext.current

    // variables to get data from the live locationd ata class
    val locationLiveData = remember { locationLiveData(context) }
    val currentLocationState = locationLiveData.observeAsState()
    val currentlocation = currentLocationState.value

    val cameraPositionState = rememberCameraPositionState {
        this.position =
            CameraPosition.fromLatLngZoom(/* target = */ currentlocation, /* zoom = */ 10f)}

        // Map Stylings
        val mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
            context, R.raw.map_style
        )


        val mapProperties by remember {
            mutableStateOf(
                MapProperties(
                    mapStyleOptions = mapStyleOptions
                )
            )
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
        )
        {
            marker(location = currentlocation, title = "Location", snippet = "You are here")
        }

    }
