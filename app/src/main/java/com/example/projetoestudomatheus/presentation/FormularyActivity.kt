package com.example.projetoestudomatheus.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ActivityFormularyBinding

class FormularyActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFormularyBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupForm()
    }

    private fun setupForm() = with(binding){
        cadastrar.setOnClickListener {
            val newNotice = Noticias(
                title.text.toString(),
                descricao.text.toString(),
                mensagem.text.toString()
            )
            DataBase().setItem(newNotice)
            val intent = Intent(this@FormularyActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

}