package eu.tribe.domain.domain.model


import com.google.gson.annotations.SerializedName


    class CityJson {
        @SerializedName("id")
        var id: Int? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("lat")
        var lat: Double = 0.0

        @SerializedName("lng")
        var lng: Double = 0.0
}