package com.example.ticketsystem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketsystem.R
import com.example.ticketsystem.User

class MyAdapter(private val userList : ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var listener : MyAdapterInterface ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.shop_name.text = currentItem.shop_name
        holder.shop_city.text = currentItem.shop_city
        holder.contact_no.text = currentItem.contact_no
        holder.shop_location.text = currentItem.shop_location
        holder.openingtime.text = currentItem.openingtime
        holder.closingtime.text = currentItem.closingtime
        holder.cardView.setOnClickListener {
            listener?.onClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val shop_name : TextView = itemView.findViewById(R.id.tv_shop_name)
        val shop_city : TextView = itemView.findViewById(R.id.tv_shop_city)
        val contact_no : TextView = itemView.findViewById(R.id.tv_contact)
        val shop_location : TextView = itemView.findViewById(R.id.tv_shop_location)
        val openingtime : TextView = itemView.findViewById(R.id.tv_open)
        val closingtime : TextView = itemView.findViewById(R.id.tv_close)
        val cardView : CardView = itemView.findViewById(R.id.user_list_card_view)
    }
}