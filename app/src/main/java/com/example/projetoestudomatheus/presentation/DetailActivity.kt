package com.example.projetoestudomatheus.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoestudomatheus.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.getBundleExtra("chave")
    }

}
