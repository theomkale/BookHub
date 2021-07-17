package com.omkale.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.omkale.bookhub.R
import com.omkale.bookhub.model.Book

class DashboardRecyclerAdapter(val context:Context,val itemList:ArrayList<Book>):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {
    class DashboardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textbookName:TextView=view.findViewById(R.id.txtBookName)
        val textbookAuthor:TextView=view.findViewById(R.id.txtAuthorName)
        val textbookPrice:TextView=view.findViewById(R.id.txtPrice)
        val textbookRating:TextView=view.findViewById(R.id.txtRating)
        val imageView:ImageView=view.findViewById(R.id.imgBookImage)
        val viewHolderTile:RelativeLayout=view.findViewById(R.id.viewHolderTile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book=itemList[position]
        holder.textbookName.text=book.bookName
        holder.textbookAuthor.text=book.bookAuthor
        holder.textbookPrice.text=book.bookCost
        holder.textbookRating.text=book.bookRating
        holder.imageView.setImageResource(book.bookImage)
        holder.viewHolderTile.setOnClickListener{
            Toast.makeText(context,"You have some intrest in ${holder.textbookAuthor}'s work.",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}