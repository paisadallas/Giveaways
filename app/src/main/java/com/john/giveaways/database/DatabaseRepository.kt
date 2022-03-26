package com.john.giveaways.database

import com.john.giveaways.model.Giveaways


interface DatabaseRepository {
    suspend fun insertGiveaways(newGiveaways: List<Giveaways>)
    suspend fun getAllGiveaways(): List<Giveaways>
    suspend fun getGiveawaysById(searchId: Int): Giveaways
    suspend fun getGiveawaysByPlatform(platform: String): List<Giveaways>
    suspend fun deleteGiveaways(giveaways: List<Giveaways>)
}

class DatabaseRepositoryImp(
    private val databaseGiveaways:GiveawaysDao):DatabaseRepository{

    override suspend fun insertGiveaways(newGiveaways: List<Giveaways>) {
        databaseGiveaways.insertGiveaways(newGiveaways)
    }

    override suspend fun getAllGiveaways(): List<Giveaways> {
        return databaseGiveaways.getAllGiveaways()
    }

    override suspend fun getGiveawaysById(searchId: Int): Giveaways {
        return databaseGiveaways.getGiveawaysById(searchId)
    }

    override suspend fun getGiveawaysByPlatform(platform: String): List<Giveaways> {
       return databaseGiveaways.getGiveawaysByPlatform(platform)
    }

    override suspend fun deleteGiveaways(giveaways: List<Giveaways>) {
       databaseGiveaways.deleteGiveaways(giveaways)
    }
}