package com.example.projetoestudomatheus.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ActivityFormularyBinding
import com.example.projetoestudomatheus.help.value

class FormularyActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFormularyBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupForm()
    }

    private fun setupForm() = with(binding) {
        cadastrar.setOnClickListener {
            DataBase().setItem(
                Noticias(
                    titlEdt.value(),
                    descricaoEdt.value(),
                    mensagemEdt.value()
                )
            )
            Intent().apply {
                startActivity(
                    Intent(
                        this@FormularyActivity,
                        MainActivity::class.java
                    )
                )
            }
        }
    }
}