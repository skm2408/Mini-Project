package com.example.shubhammishra.miniproject
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.alert_user.*

class Choice:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_user)
        val user=intent.getIntExtra("User",1)
        postUser.setOnClickListener({
            val intent= Intent(this@Choice,Post::class.java)
            intent.putExtra("URLPOST","https://jsonplaceholder.typicode.com/posts?userId=${user}")
            startActivity(intent)
        })
        albumUser.setOnClickListener({
            val intent= Intent(this@Choice,Albums::class.java)
            intent.putExtra("AlbumUrl","https://jsonplaceholder.typicode.com/photos?albumId=${user}")
            startActivity(intent)
        })
        todosUser.setOnClickListener({
            val intent= Intent(this@Choice,TodoActivity::class.java)
            intent.putExtra("TodoUrl","https://jsonplaceholder.typicode.com/todos?userId=${user}")
            startActivity(intent)
        })
    }
}