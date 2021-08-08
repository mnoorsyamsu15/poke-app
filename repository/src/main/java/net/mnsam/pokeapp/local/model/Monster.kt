package net.mnsam.pokeapp.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "monster", indices = [Index(value = ["name"])])
data class Monster(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sprite") val spriteUri: String,
    @ColumnInfo(name = "sprite_shiny") val spriteShinyUri: String,
    // element type must be at least 1
    @ColumnInfo(name = "element_slot_1") val elementSlot1: String,
    // 2nd element type, optional
    @ColumnInfo(name = "element_slot_2") val elementSlot2: String?,
)
