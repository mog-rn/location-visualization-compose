package com.mog_dev.mapsactivity.MapsAPI

import com.mog_dev.mapsactivity.AutoComplete.Models.PlaceApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApi {
    @GET("place/autocomplete/json")
    suspend fun getPlaceAutocomplete(
        @Query("input") input: String,
        @Query("key") key: String,
        @Query("components") components: String
    ): Response<PlaceApiResponse>
}