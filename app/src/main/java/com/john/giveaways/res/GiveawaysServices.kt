package com.john.giveaways.res

import com.john.giveaways.model.Giveaways
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiveawaysServices {

    @GET(GIVEAWAYS_PATH)
    suspend fun getAllGiveaways(
    @Query("sort-by")    orderBy:String
    ):Response<List<Giveaways>>

    @GET(BASE_URL)
    suspend fun getGiveawaysByPlatform(
      @Query("platform")  platform:String
    ):Response<List<Giveaways>>

    //https://www.gamerpower.com/api/giveaways
    companion object{
        const val BASE_URL ="https://www.gamerpower.com/api/"
        const val GIVEAWAYS_PATH = "giveaways"
    }
}