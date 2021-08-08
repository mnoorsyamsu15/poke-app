package net.mnsam.pokeapp.repository.list

import net.mnsam.pokeapp.local.model.Monster
import net.mnsam.pokeapp.remote.monster.model.DetailDTO

object Mapper {

    fun List<DetailDTO>.toMonsterEntities(): List<Monster> {
        val result = mutableListOf<Monster>()
        for (item in this) {
            val entity = Monster(
                id = 0,
                name = item.name,
                elementSlot1 = item.types[0].realType.name,
                elementSlot2 = item.types.getOrNull(1)?.realType?.name,
                spriteShinyUri = item.sprites.frontShiny,
                spriteUri = item.sprites.frontDefault,
            )
            result.add(entity)
        }
        return result
    }

    fun List<Monster>.toMonsterDomain(): List<MonsterDomain> {
        val result = mutableListOf<MonsterDomain>()
        for (item in this) {
            val elements = mutableListOf(item.elementSlot1)
            item.elementSlot2?.let { elements.add(it) }
            val domain = MonsterDomain(
                name = item.name,
                element = elements,
                spriteDefaultUrl = item.spriteUri,
                spriteShinyUrl = item.spriteShinyUri,
            )
            result.add(domain)
        }
        return result
    }
}
