package com.oceanbrasil.ocean_jornada_android_maio_2023.view.hints

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oceanbrasil.ocean_jornada_android_maio_2023.R
import com.oceanbrasil.ocean_jornada_android_maio_2023.viewmodel.HintsViewModel

class HintsListActivity : AppCompatActivity() {

    private val hintsViewModel: HintsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hints_list)

        // Pegar a referência da RecyclerView
        val rvHints = findViewById<RecyclerView>(R.id.rvHints)

        // Inicializar LayoutManager
        val layoutManager = LinearLayoutManager(this)
        rvHints.layoutManager = layoutManager

        // Inicializar Adapter (Precisamos criar o Adapter)
        hintsViewModel.hints.observe(this) {
            val adapter = HintsListAdapter(it)
            rvHints.adapter = adapter
        }
    }
}
