package net.mnsam.pokeapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.mnsam.pokeapp.local.dao.MonsterDao
import net.mnsam.pokeapp.local.model.Monster

@Database(entities = [Monster::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun provideMonsterDao(): MonsterDao
}
