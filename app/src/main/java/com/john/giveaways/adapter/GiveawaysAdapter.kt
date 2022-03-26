package com.john.giveaways.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.giveaways.databinding.GiveawaysItemBinding
import com.john.giveaways.model.Giveaways
import com.squareup.picasso.Picasso

class GiveawaysAdapter(
    private val giveaways:MutableList<Giveaways> = mutableListOf()
):RecyclerView.Adapter<GiveawaysHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiveawaysHolder {

        return GiveawaysHolder(GiveawaysItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GiveawaysHolder, position: Int) {
        holder.bind(giveaways[position])
    }

    fun update(newGiveaways: List<Giveaways>){
        giveaways.clear()
        giveaways.addAll(newGiveaways)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = giveaways.size
}

class GiveawaysHolder(
 private val binding: GiveawaysItemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(giveawaysItem: Giveaways){
        binding.tvTitle.text= giveawaysItem.title
        binding.tvType.text = giveawaysItem.type
        binding.tvPlatforms.text = giveawaysItem.platforms
        binding.tvPrice.text = giveawaysItem.worth
        binding.imgGame

        if (giveawaysItem.image != ""){
            Picasso.get().load(giveawaysItem.image)
                .resize(200,200)
                .centerCrop()
                .into(binding.imgGame)
        }
    }
}