package pl.szymon.starships.models

data class StarShip(
    val id: Int,
    val name: String,
    val manufacturer: String,
    val passengers: String,
    val crew: String,
    val cargoCapacity: String,
    val maxSpeed: String
)