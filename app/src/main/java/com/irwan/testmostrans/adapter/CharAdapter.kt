package com.irwan.testmostrans.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irwan.testmostrans.api.ResultsItem
import com.irwan.testmostrans.databinding.ItemCharBinding

class CharAdapter : RecyclerView.Adapter<CharAdapter.CharViewHolder>() {

    private lateinit var onItemClickCallback: CharAdapter.OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: CharAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val results: MutableList<ResultsItem> = mutableListOf()

    fun setData(newChars: List<ResultsItem>) {
        results.clear()
        results.addAll(newChars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharBinding.inflate(inflater, parent, false)
        return CharViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        val char = results[position]
        holder.binding.tvName.text = char.name
        var img = char.image

        Glide.with(holder.itemView)
            .load(img)
            .circleCrop()
            .into(holder.binding.imgPhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(results[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = results.size

    class CharViewHolder(var binding: ItemCharBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: ResultsItem)
    }
}