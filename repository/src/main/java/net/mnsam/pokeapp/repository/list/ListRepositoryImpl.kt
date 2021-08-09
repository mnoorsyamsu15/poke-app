package net.mnsam.pokeapp.repository.list

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import net.mnsam.pokeapp.local.PokemonDatabase
import net.mnsam.pokeapp.local.dao.MonsterDao
import net.mnsam.pokeapp.remote.monster.model.DetailDTO
import net.mnsam.pokeapp.remote.monster.service.MonsterApi
import net.mnsam.pokeapp.repository.list.Mapper.toMonsterDomain
import net.mnsam.pokeapp.repository.list.Mapper.toMonsterEntities
import net.mnsam.pokeapp.repository.parseRetrofitError
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val pokemonDatabase: PokemonDatabase,
    private val monsterApi: MonsterApi
) : ListRepository {

    private val monsterDao: MonsterDao get() = pokemonDatabase.provideMonsterDao()

    override fun getMonster(
        offset: Int,
        limit: Int
    ): Flow<List<MonsterDomain>> {
        return flow {
            try {
                coroutineScope {
                    // launch a separate job to fetch from network
                    launch { fetchMonster(offset, limit) }
                    // observe local db as source of truth
                    monsterDao.getAllMonsters()
                        .collect {
                            if (it.isNotEmpty()) {
                                this@flow.emit(it.toMonsterDomain())
                            }
                        }
                }
            } catch (t: Throwable) {
                val message = t.parseRetrofitError()
                throw Exception(message)
            }
        }
    }

    override suspend fun fetchMonster(offset: Int, limit: Int) {
        val monsterList = monsterApi.getMonsters(offset, limit).results
        val detailJobs = mutableListOf<Deferred<DetailDTO>>()
        for (monster in monsterList) {
            val job = coroutineScope { async { monsterApi.getDetail(monster.name) } }
            detailJobs.add(job)
        }
        monsterDao.clearTable()
        monsterDao.insert(detailJobs.awaitAll().toMonsterEntities())
    }
}
