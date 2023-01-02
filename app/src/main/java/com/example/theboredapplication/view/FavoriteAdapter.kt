package com.example.theboredapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theboredapplication.R
import com.example.theboredapplication.model.QuoteEntity

class FavoriteAdapter (val quoteList: MutableList<QuoteEntity>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quote_holder, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.quote_str.text = quoteList[position].act
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }


    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val quote_str = itemView.findViewById<TextView>(R.id.quote_str)
    }


}