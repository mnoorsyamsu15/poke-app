package net.mnsam.pokeapp.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.mnsam.pokeapp.local.model.Monster

@Dao
interface MonsterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(monsters: List<Monster>)

    @Query("SELECT * FROM monster")
    fun getAllMonsters(): Flow<List<Monster>>

    @Query("DELETE FROM monster")
    suspend fun clearTable()
}
