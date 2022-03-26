package com.john.giveaways.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.giveaways.database.DatabaseRepository
import com.john.giveaways.res.GiveawaysRepository
import com.john.giveaways.util.GiveawaysStates
import com.john.giveaways.util.PlatformType
import com.john.giveaways.util.SortType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GiveawaysViewModel(
    private val networkRepo : GiveawaysRepository,
    private val databaseRepo: DatabaseRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :ViewModel(){

    init {

    }

    var platform : PlatformType = PlatformType.ANDROID

    private val _sortedGiveaways: MutableLiveData<GiveawaysStates> = MutableLiveData(GiveawaysStates.LOADING)
    val giveaways : LiveData<GiveawaysStates> get () = _sortedGiveaways

    fun getSortedGiveaways(sortBy: SortType = SortType.DATE){
        viewModelScope.launch(ioDispatcher){
            try {
                val response = networkRepo.getAllGiveaways(sortBy)
                if (response.isSuccessful){
                    response.body()?.let {
                   // Log.d("RESPOND",it[1].toString())
                        databaseRepo.insertGiveaways(it)
                        val localData = databaseRepo.getAllGiveaways()
                        _sortedGiveaways.postValue((GiveawaysStates.SUCCESS(localData)))
                    }
                }
            } catch (e: Exception){
                throw Exception("No respond")
            }
        }
    }

}