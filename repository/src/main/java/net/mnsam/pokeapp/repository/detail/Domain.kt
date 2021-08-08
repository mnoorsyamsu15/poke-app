package net.mnsam.pokeapp.repository.detail

data class MonsterDetail(val name: String, val stats: List<Stats>)
data class Stats(val name: String, val number: Int, val total: Int)
