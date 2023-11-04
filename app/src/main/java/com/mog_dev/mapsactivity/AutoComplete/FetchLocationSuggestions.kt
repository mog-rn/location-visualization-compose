package com.mog_dev.mapsactivity.AutoComplete

import com.mog_dev.mapsactivity.AutoComplete.Models.PlaceApiResponse
import com.mog_dev.mapsactivity.AutoComplete.Models.PlacePrediction
import com.mog_dev.mapsactivity.MapsAPI.GooglePlacesApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


suspend fun fetchLocationSuggestions(query: String): List<PlacePrediction> {
    val apiKey = "AIzaSyDeu-FUgFKevInJWsTaBRAxFzavY8Okl_8"
    val components = "country:KE"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val placesApi = retrofit.create(GooglePlacesApi::class.java)
    val response: Response<PlaceApiResponse> = placesApi.getPlaceAutocomplete(query, apiKey, components)

    return if (response.isSuccessful) {
        val placeApiResponse = response.body()
        placeApiResponse?.let {
            if (it.status == "OK") {
                it.predictions
            } else {
                emptyList()
            }
        } ?: emptyList()
    } else {
        emptyList()
    }
}
