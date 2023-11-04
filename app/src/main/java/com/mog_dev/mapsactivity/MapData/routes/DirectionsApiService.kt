package com.mog_dev.mapsactivity.MapData

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DirectionsApiService {
    @Headers(
        "X-Goog-FieldMask: routes.polyline.encodedPolyline",
        "X-Goog-FieldMask: routes.duration",
        "X-Goog-Api-Key: AIzaSyDeu-FUgFKevInJWsTaBRAxFzavY8Okl_8"
        )
    @POST(".")
    suspend fun getRouteData(
     @Body request: RouteRequest

    ): Response<RouteResponse>
}


