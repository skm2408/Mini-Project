package com.example.shubhammishra.miniproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shubhammishra.miniproject.R
import kotlinx.android.synthetic.main.list_view_post.view.*

class PostAdapter(var postarray:ArrayList<PostPojo>):RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder{
        val lf=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return PostViewHolder(lf.inflate(R.layout.list_view_post,parent,false))
    }

    override fun getItemCount(): Int =postarray.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.tvUserid.text="ID:"+postarray[position].uid.toString()
        holder.itemView.tvTitle.text="Title:\n"+postarray[position].title
        holder.itemView.tvBody.text="Body:\n"+postarray[position].body
    }
    class PostViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

}