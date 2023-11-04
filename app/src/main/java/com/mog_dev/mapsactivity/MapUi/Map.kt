package com.mog_dev.mapsactivity.MapUi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.mog_dev.mapsactivity.MapData.locationLiveData
import com.mog_dev.mapsactivity.R

@Composable
fun Map() {
    val context = LocalContext.current

    // variables to get data from the livelocationdata class
    val locationLiveData  = remember { locationLiveData(context) }
    val currentLocationState = locationLiveData.observeAsState()
    val currentlocation = if (currentLocationState.value != null) {
        LatLng(currentLocationState.value!!.latitude, currentLocationState.value!!.longitude)
    } else {
        LatLng(-1.292066, 36.821945)// Default LatLng
    }

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
