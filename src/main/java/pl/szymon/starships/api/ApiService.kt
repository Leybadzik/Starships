package pl.szymon.starships.api

import pl.szymon.starships.api.dto.RootDto
import pl.szymon.starships.api.dto.StarShipDto
import pl.szymon.starships.models.StarShip
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object ApiService {
    private const val BASE_URL = "https://swapi.dev/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiInterface::class.java)

    suspend fun getItemsFromApi(): List<StarShip> {
        delay(200L)
        val response = try {
            service.getItems()
        } catch (ex: Exception) {
            return emptyList()
        }
        return if (response.isSuccessful) {
            response.body()
                ?.results
                ?.map { mapToStarShip(it) }
                ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getItemFromApi(id: Int): StarShip? {
        delay(200L)
        val response = try {
            service.getItemById(id)
        } catch (ex: Exception) {
            return null
        }
        return if (response.isSuccessful) {
            response.body()
                ?.let { mapToStarShip(it) }
        } else {
            null
        }
    }

    interface ApiInterface {
        @GET("/api/starships")
        suspend fun getItems(): Response<RootDto>

        @GET("/api/starships/{id}")
        suspend fun getItemById(@Path("id") id: Int): Response<StarShipDto>
    }

    private fun mapToStarShip(dto: StarShipDto) = StarShip(
        dto.getId(),
        dto.name,
        dto.manufacturer,
        dto.passengers,
        dto.crew,
        dto.cargoCapacity,
        dto.maxSpeed
    )
}