package com.john.giveaways.view


import androidx.fragment.app.Fragment
import com.john.giveaways.adapter.GiveawaysAdapter
import com.john.giveaways.viewmodel.GiveawaysViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
open class BaseFragment : Fragment() {

     protected val giveawaysModelView : GiveawaysViewModel by sharedViewModel()

    protected val giveawaysAdapter by lazy {
        GiveawaysAdapter()
    }
}