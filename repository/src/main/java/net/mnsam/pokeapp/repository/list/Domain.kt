package net.mnsam.pokeapp.repository.list

data class MonsterDomain(
    val name: String,
    val spriteDefaultUrl: String,
    val spriteShinyUrl: String,
    val element: List<String>,
)
