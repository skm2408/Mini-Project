package com.example.shubhammishra.miniproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.shubhammishra.miniproject.R
import kotlinx.android.synthetic.main.list_view_users.view.*

class UserAdapter(var userArray: ArrayList<UsersPojo>,val printPosition:(user:UsersPojo)->Unit):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val lf=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        return UserViewHolder(lf.inflate(R.layout.list_view_users,parent,false))
    }

    override fun getItemCount(): Int =userArray.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.userId.text="ID: "+userArray[position].id.toString()
        holder.itemView.tvName.text="NAME: "+userArray[position].name
        holder.itemView.tvEmail.text="Email: "+userArray[position].email
        holder.itemView.tvAddress.text="Address:\n"+userArray[position].address
        holder.itemView.tvWeb.text="Website Link: "+userArray[position].website
        holder.itemView.setOnClickListener({
            printPosition(userArray[position])
        })
    }

    class UserViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}