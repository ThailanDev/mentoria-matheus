package com.example.projetoestudomatheus.presentation.form

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.projetoestudomatheus.R
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.data.Noticias
import com.example.projetoestudomatheus.databinding.ActivityFormularyBinding
import com.example.projetoestudomatheus.presentation.listar.ListActivity
import com.google.android.material.textfield.TextInputLayout

class FormularyActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFormularyBinding.inflate(layoutInflater) }
    private val viewModel: FormularyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        isToUpdate()
        observer()
        setupRegisterAndFavoriteClick()
    }

    private fun isToUpdate(){
        intent.extras?.getString(TITLE_KEY)?.let { identification ->
            updateItem(identification)
        }
    }

    private fun updateItem(identification: String) = with(binding) {
        viewModel.getItem(identification)
        setupUpdateClick(identification)
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

    private fun setupRegisterAndFavoriteClick() = with(binding) {
        favorito.setOnClickListener {
            viewModel.updateStateFavorite()
        }
        cadastrar.setOnClickListener {
            if (isValidFields()) {
                createNewObject(
                    Noticias(
                        title = titleEdit.text.toString(),
                        description = descriptionEdit.text.toString(),
                        mensagem = menssageEdit.text.toString(),
                        favoritado = isFavorite
                    )
                )
                navigateToMainActivity()
            }
        }
    }

    private fun setupUpdateClick(
        identification: String
    ) = with(binding) {
        atualizar.setOnClickListener {
            DataBase().updateItem(identification, Noticias(
                title = titleEdit.text.toString(),
                description = descriptionEdit.text.toString(),
                mensagem = menssageEdit.text.toString(),
                favoritado = isFavorite
            ))
            navigateToMainActivity()
        }
    }

    private fun observer(){
        viewModel.state.observe(this) { state ->
            state?.noticia?.let {
                configForm(it)
                updateStateFavorite(state.isFavorite)
                updateStateButton(state.isEnabled)
            }
        }
    }

    private fun configForm(noticia: Noticias) = with(binding){
        titleEdit.setText(noticia.title)
        descriptionEdit.setText(noticia.description)
        menssageEdit.setText(noticia.mensagem)
    }

    private fun updateStateFavorite(isFavorite: Boolean) = with(binding){
        val favorite = if (isFavorite) {
            R.drawable.start_full
        } else {
            R.drawable.start_border
        }
        favorito.setBackgroundResource(favorite)
    }

    private fun updateStateButton(enabled: Boolean) = with(binding) {
        cadastrar.isEnabled = enabled
        if(!enabled){
            cadastrar.setBackgroundColor(Color.GRAY)
            cadastrar.setTextColor(Color.WHITE)
        }
    }

    companion object {
        const val TITLE_KEY = "titulo"
    }

}