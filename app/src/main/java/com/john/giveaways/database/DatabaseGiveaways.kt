package com.john.giveaways.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.john.giveaways.model.Giveaways

@Database(
    entities = [Giveaways::class],
    version = 1
)
abstract class DatabaseGiveaways:RoomDatabase() {
    abstract fun getGiveawaysDao() : GiveawaysDao
}

@Dao
interface GiveawaysDao{
    @Insert(onConflict = REPLACE)
    suspend fun insertGiveaways(newGiveaways: List<Giveaways>)

    @Query("SELECT * FROM giveaways")
    suspend fun getAllGiveaways():List<Giveaways>

    @Query("SELECT * FROM giveaways WHERE id = :searchId")
    suspend fun getGiveawaysById(searchId: Int) : Giveaways

    @Query("SELECT * FROM giveaways WHERE platforms = :platform")
    suspend fun getGiveawaysByPlatform(platform:String):List<Giveaways>

    @Delete
    suspend fun deleteGiveaways(giveaways: List<Giveaways>)

}