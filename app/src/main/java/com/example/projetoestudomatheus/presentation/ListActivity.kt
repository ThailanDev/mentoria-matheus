package com.example.projetoestudomatheus.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ListActivityBinding
import com.example.projetoestudomatheus.presentation.adapter.ListActivityAdapter

class ListActivity : AppCompatActivity() {

    private val binding by lazy { ListActivityBinding.inflate(layoutInflater) }
    private val list = DataBase().getList()
    private var adapter = ListActivityAdapter(::delete, ::update, ::favorite)

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
            val intent = Intent(this@ListActivity, FormularyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun delete(item: Noticias) {
        DataBase().delete(item.title)
        adapter.notifyDataSetChanged()
    }

    private fun favorite(item: Noticias) {
        DataBase().updateItem(item.title, item)
        adapter.notifyDataSetChanged()
    }

    private fun update(item: Noticias) {
        val intent = Intent(this@ListActivity, FormularyActivity::class.java)
        intent.putExtra("titulo", item.title)
        startActivity(intent)
    }

}