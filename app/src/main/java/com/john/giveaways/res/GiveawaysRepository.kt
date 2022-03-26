package com.john.giveaways.res

import com.john.giveaways.model.Giveaways
import com.john.giveaways.util.PlatformType
import com.john.giveaways.util.SortType
import retrofit2.Response

interface GiveawaysRepository{
    suspend fun getAllGiveaways(sortedBy:SortType): Response<List<Giveaways>>
    suspend fun getGiveawaysByPlatform(platform: PlatformType):Response<List<Giveaways>>
}

class GiveawaysRepositoryImpl(
    private val giveawaysServices: GiveawaysServices
) : GiveawaysRepository{

    override suspend fun getAllGiveaways(sortedBy: SortType): Response<List<Giveaways>> =
        giveawaysServices.getAllGiveaways(sortedBy.realValue)

    override suspend fun getGiveawaysByPlatform(platform: PlatformType): Response<List<Giveaways>> =
        giveawaysServices.getGiveawaysByPlatform(platform.realValue)
}