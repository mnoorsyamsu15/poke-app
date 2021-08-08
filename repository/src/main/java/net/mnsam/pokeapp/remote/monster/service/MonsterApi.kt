package net.mnsam.pokeapp.remote.monster.service

import kotlinx.coroutines.Deferred
import net.mnsam.pokeapp.remote.monster.model.DetailDTO
import net.mnsam.pokeapp.remote.monster.model.MonsterDTO
import net.mnsam.pokeapp.remote.monster.model.MonsterListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MonsterApi {
    @GET("v2/pokemon")
    suspend fun getMonsters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MonsterListDTO

    @GET("v2/pokemon/{name}")
    suspend fun getDetail(@Path("name") name: String): DetailDTO
}
