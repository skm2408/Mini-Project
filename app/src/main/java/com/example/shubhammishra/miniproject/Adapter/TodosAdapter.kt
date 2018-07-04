package com.example.shubhammishra.miniproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shubhammishra.miniproject.R
import kotlinx.android.synthetic.main.list_view_todos.view.*

class TodosAdapter(var todoArray:ArrayList<Todos>):RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val lf=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        return TodoViewHolder(lf.inflate(R.layout.list_view_todos,parent,false))
    }

    override fun getItemCount(): Int=todoArray.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.todoId.text="ID:"+todoArray[position].id.toString()
        holder.itemView.todoTitle.text="\nTitle:\n"+todoArray[position].title
        holder.itemView.todocheckList.isChecked=todoArray[position].check
    }

    class TodoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}