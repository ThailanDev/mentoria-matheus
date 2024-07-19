package com.example.projetoestudomatheus.data

class DataBase {

    fun setItem(item:Noticias){
        list.add(item)
    }

    fun getList() = list


    companion object {
        private val list:MutableList<Noticias> = mutableListOf()
    }
}