package net.mnsam.pokeapp.remote.monster.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MonsterListDTO(@Json(name = "results") val results: List<MonsterDTO>)

@JsonClass(generateAdapter = true)
data class MonsterDTO(@Json(name = "name") val name: String, @Json(name = "url") val url: String)

@JsonClass(generateAdapter = true)
data class DetailDTO(
    @Json(name = "name") val name: String,
    @Json(name = "sprites") val sprites: SpritesDTO,
    @Json(name = "types") val types: List<TypeDTO>,
)

@JsonClass(generateAdapter = true)
data class TypeDTO(
    @Json(name = "slot") val slot: Int,
    @Json(name = "type") val realType: RealTypeDTO
)

@JsonClass(generateAdapter = true)
data class RealTypeDTO(@Json(name = "name") val name: String)

@JsonClass(generateAdapter = true)
data class SpritesDTO(
    @Json(name = "front_default") val frontDefault: String,
    @Json(name = "front_shiny") val frontShiny: String,
)
