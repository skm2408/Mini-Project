package com.example.shubhammishra.miniproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cardPost.setOnClickListener({
             val intent=Intent(this,Post::class.java)
             val url="https://jsonplaceholder.typicode.com/posts"
             intent.putExtra("URLPOST",url)
             startActivity(intent)
        })
        cardAlbums.setOnClickListener({
            val intent=Intent(this,Albums::class.java)
            intent.putExtra("AlbumUrl","https://jsonplaceholder.typicode.com/photos")
            startActivity(intent)
        })
        cardTodos.setOnClickListener({
            val intent=Intent(this,TodoActivity::class.java)
            val url="https://jsonplaceholder.typicode.com/todos"
            intent.putExtra("TodoUrl",url)
            startActivity(intent)
        })
        cardUsers.setOnClickListener({
            val intent=Intent(this,Users::class.java)
            val url="https://jsonplaceholder.typicode.com/users"
            intent.putExtra("UserUrl",url)
            startActivity(intent)
        })
    }
}
