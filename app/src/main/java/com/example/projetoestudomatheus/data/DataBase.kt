package com.example.projetoestudomatheus.data

class DataBase {

    fun setItem(item: Noticias) {
        list.add(item)
    }

    fun getList() = list

    fun delete(key: String) {
        val newList = list.filter { key != it.title }
        list.clear()
        list.addAll(newList)
    }

    fun getItem(titulo: String) = list.find { it.title == titulo }

    fun updateItem(key:String, item: Noticias) {
        list.forEach {
            if (it.title == key) {
                it.title = item.title
                it.mensagem = item.mensagem
                it.description = item.description
                it.favoritado = item.favoritado
            }
        }
    }

    companion object {
        private val list: MutableList<Noticias> = mutableListOf()
    }
}