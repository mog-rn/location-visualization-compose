package com.mog_dev.mapsactivity.AutoComplete.Models

import com.google.android.gms.maps.model.LatLng

data class PlaceApiResponse(
    val status: String,
    val predictions: List<PlacePrediction>
)

data class PlacePrediction(
    val description: String,
    val placeId: String,
    val latLng: LatLng,
    val name: String,
    val address: String,
)