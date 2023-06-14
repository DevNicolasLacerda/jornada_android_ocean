package com.oceanbrasil.ocean_jornada_android_maio_2023.view.hints

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oceanbrasil.ocean_jornada_android_maio_2023.R
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.domain.Hint

class HintsListAdapter(private val items: List<Hint>) : RecyclerView.Adapter<HintsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Hint) {
            val tvHintName = itemView.findViewById<TextView>(R.id.tvHintName)

            tvHintName.text = "Dica ${item.id}: ${item.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Criamos a View a partir do XML
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.hint_item, parent, false)

        // Criamos o ViewHolder com a View e retornamos para o onCreateViewHolder
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Acessamos o dado na lista a partir do `position` que o Android trouxe
        val item = items[position]

        // Realizamos o bind, que carregará os dados no ViewHolder, que conhece a View
        // Para isso, executamos o `bindView` enviando os dados específicos a serem exibidos
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
