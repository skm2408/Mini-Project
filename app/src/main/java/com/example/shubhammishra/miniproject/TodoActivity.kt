package com.example.shubhammishra.miniproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.shubhammishra.miniproject.Adapter.Todos
import com.example.shubhammishra.miniproject.Adapter.TodosAdapter
import kotlinx.android.synthetic.main.activity_todo.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        var url=intent.getStringExtra("TodoUrl")
        GetTodo().execute(url)
        btnTodos.setOnClickListener({
            val uid=searchTodos.text.toString()
            url="https://jsonplaceholder.typicode.com/todos?userId=$uid"
            GetTodo().execute(url)
        })
    }
    inner class GetTodo:AsyncTask<String,Unit,String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d("URLTODOS","$result")
            var todoArray=ArrayList<Todos>()
            try {
                val jsonArray=JSONArray("$result")
                for(i in 0..jsonArray.length()-1)
                {
                    val item=jsonArray.getJSONObject(i)
                    val id=item.getInt("userId")
                    val title=item.getString("title")
                    val completed=item.getBoolean("completed")
                    todoArray.add(Todos(id,title,completed))
                }
            }
            catch (e:JSONException)
            {
                Log.e("Error","Invalid Json")
            }
           val todosAdapter=TodosAdapter(todoArray)
            recyclerViewTodo.layoutManager=LinearLayoutManager(this@TodoActivity)
            recyclerViewTodo.adapter=todosAdapter
            Toast.makeText(this@TodoActivity,"No of items in search result=${todoArray.size}",Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg params: String?): String {
            var str=""
            try {
                val url=URL(params[0])
                val httpURLConnection=url.openConnection() as HttpURLConnection
                val scanner=Scanner(httpURLConnection.inputStream)
                scanner.useDelimiter("\\A")
                if(scanner.hasNext())
                {
                    str=scanner.next()
                }
            }
            catch (e:IOException)
            {
                Log.e("Error","Invalid Url")
            }
            return str
        }
    }
}
