package com.example.paginationdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paginationdemo.databinding.ItemViewBinding
import com.example.paginationdemo.model.User

class RvAdapter(private val list : MutableList<User>) : RecyclerView.Adapter<RvAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding : ItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindData(position: Int){
            binding.apply {
                id.text = list[position].name
            }
            /*Glide.with(itemView.context).load(list[position].).into(binding.imgIv)*/
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RvAdapter.ItemViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvAdapter.ItemViewHolder, position: Int) {
        holder.bindData(position)
    }

    fun updateList(data : List<User>){
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}