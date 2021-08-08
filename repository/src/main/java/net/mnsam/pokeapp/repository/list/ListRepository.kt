package net.mnsam.pokeapp.repository.list

import kotlinx.coroutines.flow.Flow

interface ListRepository {

    fun getMonster(offset: Int, limit: Int): Flow<List<MonsterDomain>>

    suspend fun fetchMonster(offset: Int, limit: Int)
}
