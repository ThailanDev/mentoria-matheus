package com.example.projetoestudomatheus.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import com.example.projetoestudomatheus.R
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ActivityFormularyBinding
import com.google.android.material.textfield.TextInputLayout

class FormularyActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFormularyBinding.inflate(layoutInflater) }
    var foiFavoritado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        intent.extras?.getString("titulo")?.let { identificador ->

            val meuItem = DataBase().getItem(identificador)
            foiFavoritado = meuItem?.favoritado ?: false

            with(binding) {

                titleEdit.setText(meuItem?.title.toString())
                descriptionEdit.setText(meuItem?.description.toString())
                menssageEdit.setText(meuItem?.mensagem.toString())

                if (meuItem?.favoritado == true) {
                    binding.favorito.setBackgroundResource(R.drawable.start_full)
                } else {
                    binding.favorito.setBackgroundResource(R.drawable.start_border)
                }

                cadastrar.isEnabled = false
                cadastrar.setBackgroundColor(Color.GRAY)
                cadastrar.setTextColor(Color.WHITE)
                atualizar.setOnClickListener {
                    val objetoNovo = Noticias(
                        title = titleEdit.text.toString(),
                        description = descriptionEdit.text.toString(),
                        mensagem = menssageEdit.text.toString(),
                        favoritado = foiFavoritado
                    )
                    DataBase().updateItem(identificador, objetoNovo)
                    navigateToMainActivity()
                }
            }
        }

        binding.favorito.setOnClickListener {
            foiFavoritado = !foiFavoritado
            if (foiFavoritado) {
                binding.favorito.setBackgroundResource(R.drawable.start_full)
            } else {
                binding.favorito.setBackgroundResource(R.drawable.start_border)
            }
        }

        criarNovaNoticia()
    }

    private fun navigateToMainActivity() {
        Intent().apply {
            startActivity(
                Intent(
                    this@FormularyActivity,
                    ListActivity::class.java
                )
            )
        }
    }

    private fun criarNovaNoticia() = with(binding) {
        cadastrar.setOnClickListener {
            if (isValidFields()) {
                createNewObject(
                    Noticias(
                        title = titleEdit.text.toString(),
                        description = descriptionEdit.text.toString(),
                        mensagem = menssageEdit.text.toString(),
                        favoritado = foiFavoritado
                    )
                )
                navigateToMainActivity()
            }
        }
    }

    private fun createNewObject(noticia: Noticias) {
        DataBase().setItem(noticia)
    }

    private fun isValidFields(): Boolean = with(binding) {
        root.children.forEach { component ->
            if (component is TextInputLayout) {
                if (component.editText?.text.toString().isBlank()) {
                    component.error = "Campo obrigatório"
                    component.isErrorEnabled
                } else {
                    component.error = null
                    component.isErrorEnabled = false
                }
            }
        }
        return titleEdit.text.toString().isNotBlank() &&
                descriptionEdit.text.toString().isNotBlank() &&
                menssageEdit.text.toString().isNotBlank()
    }

}