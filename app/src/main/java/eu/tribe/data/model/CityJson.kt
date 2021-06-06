package eu.tribe.data.model

import com.google.gson.annotations.SerializedName

class CityJson(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)