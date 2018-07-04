package com.example.shubhammishra.miniproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.shubhammishra.miniproject.Adapter.PostAdapter
import com.example.shubhammishra.miniproject.Adapter.PostPojo
import kotlinx.android.synthetic.main.activity_post.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class Post : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        var url:String=""
        intent?.let {
            url=it.getStringExtra("URLPOST")
            GetPost().execute(url)
        }
        btnPost.setOnClickListener({
            val searchId=searchPost.text.toString()
            url="https://jsonplaceholder.typicode.com/posts?userId=$searchId"
            GetPost().execute(url)
        })
    }
    inner class GetPost:AsyncTask<String,Unit,String>(){
        override fun onPostExecute(result: String?) {
            var Postarray=ArrayList<PostPojo>()
            try{
                var arrayList=JSONArray("$result")
                Log.d("Size","${arrayList.length()}")
            for(i in 0..arrayList.length()-1)
            {
                val item=arrayList.getJSONObject(i)
                val id=item.getInt("userId")
                val title=item.getString("title")
                val body=item.getString("body")
                Postarray.add(PostPojo(id,title,body))
            }
            }
            catch (e:JSONException)
            {
                Log.e("Error","Invalid Json")
            }
            var postAdapter=PostAdapter(Postarray)
            recyclerView.layoutManager=LinearLayoutManager(this@Post)
            recyclerView.adapter=postAdapter
            Toast.makeText(this@Post,"No of data found with this match=${Postarray.size}", Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg params: String?): String {
            var res:String=" "
           try {
               val url=URL(params[0])
               val httpURLConnection=url.openConnection()as HttpURLConnection
               val inputStream=httpURLConnection.inputStream
               val scanner=Scanner(inputStream)
               scanner.useDelimiter("\\A")
               if(scanner.hasNext())
               {
                   res=scanner.next()
               }
           }
           catch (e:IOException)
           {
               Log.e("Error","Url not found/Url not correct")
           }
            return res
        }
    }
}
