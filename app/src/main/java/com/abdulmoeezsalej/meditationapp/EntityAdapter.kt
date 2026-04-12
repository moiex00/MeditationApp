package com.abdulmoeezsalej.meditationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntityAdapter(
    private var entities: List<Entity>,
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textArtworkTitle: TextView = itemView.findViewById(R.id.textArtworkTitle)
        val textArtist: TextView = itemView.findViewById(R.id.textArtist)
        val textMedium: TextView = itemView.findViewById(R.id.textMedium)
        val textYear: TextView = itemView.findViewById(R.id.textYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.textArtworkTitle.text = entity.artworkTitle
        holder.textArtist.text = "Artist: ${entity.artist}"
        holder.textMedium.text = "Medium: ${entity.medium}"
        holder.textYear.text = "Year: ${entity.year}"
        holder.itemView.setOnClickListener {
            onItemClick(entity)
        }
    }

    override fun getItemCount() = entities.size

    fun updateEntities(newEntities: List<Entity>) {
        entities = newEntities
        notifyDataSetChanged()
    }
}