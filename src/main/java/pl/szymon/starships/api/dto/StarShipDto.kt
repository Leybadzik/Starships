package pl.szymon.starships.api.dto

import com.google.gson.annotations.SerializedName

data class StarShipDto(
    val name: String,
    val manufacturer: String,
    @SerializedName("starship_class")
    val starshipClass: String,
    val url: String,
    val passengers: String,
    val crew: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    @SerializedName("max_atmosphering_speed")
    val maxSpeed: String
) {
    fun getId(): Int {
        val betweenSlashes = url.substringBeforeLast("/")
            .substringAfterLast("/")
        return betweenSlashes.toInt()
    }
}
