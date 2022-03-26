package com.john.giveaways.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.john.giveaways.databinding.FragmentGiveawaysBinding
import com.john.giveaways.model.Giveaways
import com.john.giveaways.util.GiveawaysStates


class GiveawaysFragment : BaseFragment() {

    private val binding by lazy {
        FragmentGiveawaysBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.rvGiveaways.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = giveawaysAdapter
        }

        giveawaysModelView.giveaways.observe(viewLifecycleOwner){
            when(it){
                is GiveawaysStates.LOADING -> {
                    Toast.makeText(requireContext(), "loading...", Toast.LENGTH_LONG).show()
                }
                is GiveawaysStates.SUCCESS<*> -> {
                    val giveaways = it.giveaways as List<Giveaways>
                    giveawaysAdapter.update(giveaways)
                    Toast.makeText(requireContext(), "Enter Success", Toast.LENGTH_LONG).show()
                    Log.d("RESPOND",giveaways.toString())
                }
                is GiveawaysStates.ERROR ->{
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG).show()
                    Toast.makeText(requireContext(), "Error...", Toast.LENGTH_LONG).show()
                }
            }
        }

        giveawaysModelView.getSortedGiveaways()

        return binding.root
    }

}