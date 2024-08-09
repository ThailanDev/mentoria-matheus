package com.example.projetoestudomatheus.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoestudomatheus.R
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ItemRecyclerBinding

class ListActivityAdapter(
    val delete: (Noticias) -> Unit,
    val update: (Noticias) -> Unit,
    val favorite: (Noticias) -> Unit
) : ListAdapter<Noticias, ListActivityAdapter.MainViewHolder>(ListActivityAdapter) {

    inner class MainViewHolder(
        val binding: ItemRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Noticias) = with(binding) {
            titulo.text = item.title
            descricao.text = item.description
            mensagem.text = item.mensagem

            if(item.favoritado){
                binding.favoritar.setBackgroundResource(R.drawable.start_full)
            } else {
                binding.favoritar.setBackgroundResource(R.drawable.start_border)
            }

            root.setOnClickListener { update(item) }

            delete.setOnClickListener {
                Toast.makeText(binding.root.context, "DELETADO", Toast.LENGTH_SHORT).show()
                delete(item)
            }

            favoritar.setOnClickListener {
                favorite(item.copy(favoritado = !item.favoritado))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    private companion object : DiffUtil.ItemCallback<Noticias>() {
        override fun areItemsTheSame(oldItem: Noticias, newItem: Noticias): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Noticias, newItem: Noticias): Boolean {
            return oldItem == newItem
        }
    }
}