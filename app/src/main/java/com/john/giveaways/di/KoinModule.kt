package com.john.giveaways.di

import android.content.Context
import androidx.room.Room
import com.john.giveaways.database.DatabaseGiveaways
import com.john.giveaways.database.DatabaseRepository
import com.john.giveaways.database.DatabaseRepositoryImp
import com.john.giveaways.database.GiveawaysDao
import com.john.giveaways.res.GiveawaysRepository
import com.john.giveaways.res.GiveawaysRepositoryImpl
import com.john.giveaways.res.GiveawaysServices
import com.john.giveaways.viewmodel.GiveawaysViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
val networkModule = module {
    fun providesLoggingInterceptor():HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

    fun providesNetworkServices(okHttpClient: OkHttpClient): GiveawaysServices =
        Retrofit.Builder()
            .baseUrl(GiveawaysServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GiveawaysServices::class.java)

    fun providesGiveawaysRepo(networkServices: GiveawaysServices):GiveawaysRepository=
        GiveawaysRepositoryImpl(networkServices)

    single { providesLoggingInterceptor() }
    single { providesOkHttpClient(get ()) }
    single { providesNetworkServices(get ()) }
    single { providesGiveawaysRepo(get()) }

}

val applicationModule = module {

    fun providesGiveawaysDatabase(context: Context): DatabaseGiveaways =
        Room.databaseBuilder(
            context,
            DatabaseGiveaways::class.java,
            "giveaways-db"
        ).build()

    fun providesGiveawaysDao(databaseGiveaways: DatabaseGiveaways):GiveawaysDao =
        databaseGiveaways.getGiveawaysDao()

    fun providesDatabaseRepository(databaseDao : GiveawaysDao):DatabaseRepository =
        DatabaseRepositoryImp(databaseDao)

    single {providesGiveawaysDatabase(androidContext())}
    single { providesGiveawaysDao(get()) }
    single { providesDatabaseRepository(get()) }
}

val viewModule = module {
    viewModel{GiveawaysViewModel(get(),get())}
}