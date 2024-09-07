package com.example.projetoestudomatheus.presentation.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetoestudomatheus.data.DataBase
import com.example.projetoestudomatheus.data.Noticias

class FormularyViewModel : ViewModel() {


    var state = MutableLiveData(FormularyState(Noticias()))
        private set

    fun getItem(identification: String) {
        val item = DataBase().getItem(identification)
        state.value = FormularyState(
            noticia = item,
            isEnabled = true,
            isFavorite = item?.favoritado ?: false
        )
    }

    fun updateStateFavorite() {
        val isFavorite = state.value?.isFavorite ?: false
        state.postValue(state.value?.copy(isFavorite = !isFavorite))
    }

}

data class FormularyState(
    val noticia: Noticias? = null,
    val isEnabled: Boolean = true,
    val isFavorite: Boolean = false,
)