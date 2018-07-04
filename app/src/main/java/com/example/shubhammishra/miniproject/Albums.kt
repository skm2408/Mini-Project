package com.example.shubhammishra.miniproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.shubhammishra.miniproject.Adapter.AlbumApdapter
import com.example.shubhammishra.miniproject.Adapter.AlbumPojo
import kotlinx.android.synthetic.main.activity_albums.*
import kotlinx.android.synthetic.main.activity_post.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class Albums : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        var url=intent.getStringExtra("AlbumUrl")
        albumSearch.setOnClickListener({
            val albumid=etUrl.text.toString()
            url="https://jsonplaceholder.typicode.com/photos?albumId=$albumid"
            GetAlbum().execute(url)
        })
        GetAlbum().execute(url)
    }

    inner class GetAlbum:AsyncTask<String,Unit,String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            lateinit var adapter:AlbumApdapter
           try {
               var albumArray=ArrayList<AlbumPojo>()
               val jsonArray=JSONArray("$result")
               for(i in 0..jsonArray.length()-1)
               {
                   val item=jsonArray.getJSONObject(i)
                   val id=item.getInt("albumId")
                   val title=item.getString("title")
                   val imgUrl=item.getString("url")
                   albumArray.add(AlbumPojo(id,title,imgUrl))
               }
               Log.d("SIze","${albumArray.size}")

                adapter=AlbumApdapter(albumArray)
               albumRecycler.layoutManager=LinearLayoutManager(this@Albums)
               albumRecycler.adapter=adapter
               Toast.makeText(this@Albums,"No of data found with this match=${albumArray.size}",Toast.LENGTH_SHORT).show()
           }
           catch (e:JSONException)
           {
               Log.e("Error","Invalid JSON")
           }
        }

        override fun doInBackground(vararg params: String?): String {
            var res=""
            try {
                val url=URL(params[0])
                val httpURLConnection=url.openConnection() as HttpURLConnection
                val scanner=Scanner(httpURLConnection.inputStream)
                scanner.useDelimiter("\\A")
                if(scanner.hasNext())
                {
                    res=scanner.next()
                }
            }
            catch (e:IOException)
            {
                Log.e("Error","Non existing Url or Unable to get input Stream")
            }
            return res
        }
    }
}
