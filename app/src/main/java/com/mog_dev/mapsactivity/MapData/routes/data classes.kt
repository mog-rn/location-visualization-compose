package com.mog_dev.mapsactivity.MapData

data class Polyline(
    val encodedPolyline: String
)

data class Route(
    val duration: String,
    val polyline: Polyline
)

data class RouteResponse(
    val routes: List<Route>
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)

data class Location(
    val latLng: LatLng
)

data class Origin(
    val location: com.google.android.gms.maps.model.LatLng
)

data class Destination(
    val location: com.google.android.gms.maps.model.LatLng
)

data class RouteRequest(
    val origin: Origin,
    val destination: Destination,
)
