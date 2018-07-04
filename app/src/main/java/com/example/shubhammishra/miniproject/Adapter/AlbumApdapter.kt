package com.example.shubhammishra.miniproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shubhammishra.miniproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_view_albums.view.*

class AlbumApdapter(var albumArray:ArrayList<AlbumPojo>):RecyclerView.Adapter<AlbumApdapter.AlbumViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val lf=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return AlbumViewHolder(lf.inflate(R.layout.list_view_albums,parent,false))
    }

    override fun getItemCount(): Int =albumArray.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.itemView.tvAlbum.text="ID:"+albumArray[position].id.toString()
        holder.itemView.tvAblumTitle.text="TITLE:\n"+albumArray[position].title
        Picasso.get().load("${albumArray[position].imgUrl}").resize(1000,1000).placeholder(R.drawable.albums).onlyScaleDown().into(holder.itemView.ivImage)
    }

    class AlbumViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

}