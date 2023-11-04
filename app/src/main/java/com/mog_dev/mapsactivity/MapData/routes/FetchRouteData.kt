package com.mog_dev.mapsactivity.MapData

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun FetchRouteData(
    origin: LatLng,
    destination: LatLng,
    onRouteDataFetched: (String?) -> Unit
)
{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://routes.googleapis.com/directions/v2:computeRoutes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(DirectionsApiService::class.java)
    val responseState = remember { mutableStateOf<Response<RouteResponse>?>(null) }

    val routeRequest = RouteRequest(origin = Origin(origin), destination = Destination(destination))

    LaunchedEffect(origin,destination) {
        try {
            val apiResponse = apiService.getRouteData(routeRequest)
            responseState.value = apiResponse

            // response for testing and debugging
            val responseBody = apiResponse.body()

            var encodedPolyline = MutableStateFlow<String?>(null)
            encodedPolyline.value = responseState.value?.body()?.routes?.getOrNull(0)?.polyline?.encodedPolyline

            var duration = MutableStateFlow<String?>(null)
            duration.value = responseState.value?.body()?.routes?.getOrNull(0)?.duration

//            onRouteDataFetched(encodedPolyline.value, duration.value)

            if (apiResponse.isSuccessful && responseBody != null) {
                Log.d("FetchRouteData", "API Response: $responseBody")
            } else {
                Log.e("FetchRouteData", "API Response Error: ${apiResponse.code()} - ${apiResponse.message()}")
            }
        } catch (e: Exception) {
            Log.e("FetchRouteData", "API Request Error: ${e.message}", e)
        }
    }


    val encodedPolyline = responseState.value?.body()?.routes?.getOrNull(0)?.polyline?.encodedPolyline
    onRouteDataFetched(encodedPolyline)


}
