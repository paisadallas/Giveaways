package com.john.giveaways.res

import com.john.giveaways.model.Giveaways
import com.john.giveaways.res.GiveawaysServices.Companion.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiveawaysServices {

    @GET(GIVEAWAYS_PATH)
    suspend fun getAllGiveaways(
        orderBy:String
    ):Response<Giveaways>

    @GET(BASE_URL)
    suspend fun getGiveawaysByPlatform(
        platform:String
    ):Response<Giveaways>

    //https://www.gamerpower.com/api/giveaways
    companion object{
        const val BASE_URL ="https://www.gamerpower.com/api/"
        const val GIVEAWAYS_PATH = "giveaways"
    }
}