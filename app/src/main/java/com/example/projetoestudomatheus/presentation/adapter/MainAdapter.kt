package com.example.projetoestudomatheus.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ItemRecyclerBinding

class MainAdapter(
    val onclick:(Noticias)->Unit
):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var adapterList = mutableListOf(Noticias())

    inner class MainViewHolder(
        val binding:ItemRecyclerBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item: Noticias) {
            binding.titulo.text = item.title
            binding.root.setOnClickListener {
                onclick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = adapterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = adapterList.size

    fun submitList(list: MutableList<Noticias>) {
        adapterList = list
    }
}