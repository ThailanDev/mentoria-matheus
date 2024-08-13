package com.example.projetoestudomatheus.presentation.favoritos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.databinding.ActivityFavoritosBinding
import com.example.projetoestudomatheus.presentation.adapter.ListActivityAdapter

class FavoritosActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFavoritosBinding.inflate(layoutInflater) }
    private val list = DataBase().getListFavoritos()
    private var adapter = ListActivityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
    }

    private fun setupRecycler() = with(binding) {
        adapter.submitList(list)
        recyclerview.adapter = adapter
    }


}