package com.br.titoaesj.examplesoftest.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.titoaesj.examplesoftest.R
import com.br.titoaesj.examplesoftest.data.model.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val characterRV : RecyclerView by lazy {
        findViewById(R.id.characterRV)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        characterRV.layoutManager = layoutManager

        mainViewModel.res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.results.let { results ->
                        Log.d("log", results.toString())
                        results?.let {characters ->
                            characterRV.adapter = CharacterAdapter(characters)
                        }
                    }
                }
                Status.LOADING -> {
                    Log.d("log", "loading")
                }
                Status.ERROR -> {
                    Log.d("log", "error")
                }
            }
        }
    }

}