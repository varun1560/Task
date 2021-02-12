package com.hiral.demotest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hiral.demotest.R
import com.hiral.demotest.data.network.responses.ApiResponse
import com.hiral.demotest.utils.CenterView

class CardAdapter(val context: Context, val apiResponse: List<ApiResponse>, val onItemClickListener: OnItemClickListener) :
        RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.tv_title.setText(apiResponse.get(position).title)
        holder.tv_body.setText(apiResponse.get(position).body)
    }

    override fun getItemCount(): Int {
        return apiResponse.size
    }

    inner class CardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
         var tv_title: TextView
         var tv_body: TextView
         var card_item:CardView
        init {
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
            card_item = view.findViewById(R.id.card_item)
            card_item.setOnClickListener {
                onItemClickListener.onSelected(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onSelected(position: Int)
    }
}