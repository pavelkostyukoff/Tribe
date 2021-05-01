package eu.tribe.domain.domain.model

import com.google.gson.annotations.SerializedName

class CityEntity {
    @SerializedName("cities")
    val cities : List<CityJson> = emptyList()
}