package eu.tribe.data.model

import com.google.gson.annotations.SerializedName

class CitiesJson {
    @SerializedName("cities")
    val cities : List<CityJson> = emptyList()
}