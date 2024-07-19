package com.example.projetoestudomatheus.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ActivityMainBinding
import com.example.projetoestudomatheus.presentation.adapter.MainAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val list = DataBase().getList()
    private var adapter = MainAdapter(::navigate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        setupClick()
    }

    private fun setupRecycler() = with(binding) {
        adapter.submitList(list)
        recyclerview.adapter = adapter
    }

    private fun setupClick() = with(binding) {
      floating.setOnClickListener {
          val intent = Intent(this@MainActivity, FormularyActivity::class.java)
          startActivity(intent)
      }
    }

    private fun navigate(item: Noticias) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("chave", "${item.title}")
        startActivity(intent)
    }

}