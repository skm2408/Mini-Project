package com.example.shubhammishra.miniproject

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.shubhammishra.miniproject.Adapter.UserAdapter
import com.example.shubhammishra.miniproject.Adapter.UsersPojo
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.alert_user.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class Users : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val url=intent.getStringExtra("UserUrl")
        GetUser().execute(url)
        btnUser.setOnClickListener({
            if(etUser.text.toString().equals("")==false)
            GetUser().execute("https://jsonplaceholder.typicode.com/users?id=${etUser.text.toString()}")
            else
                GetUser().execute(url)
        })
    }
    inner class GetUser:AsyncTask<String,Unit,String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var userArray=ArrayList<UsersPojo>()
            try {
                val jsonArray=JSONArray(result)
                for(i in 0..jsonArray.length()-1)
                {
                    val item=jsonArray.getJSONObject(i)
                    val id=item.getInt("id")
                    val name=item.getString("name")
                    val email=item.getString("email")
                    val address=item.getJSONObject("address")
                    val add="Street:"+address.getString("street")+"\nSuite:"+address.getString("suite")+"\nCity:"+address.getString("city")+"\nZipCode:"+address.getString("zipcode")
                    val website=item.getString("website")
                    userArray.add(UsersPojo(id,name,email,add,website))
                }
                val userAdapter=UserAdapter(userArray,{
                    user:UsersPojo->
                    /*var builder=AlertDialog.Builder(this@Users)
                    val mView=layoutInflater.inflate(R.layout.alert_user,null)
                    builder.setView(mView)
                    val dialog=builder.create()
                    dialog.show()*/
                    val intent=Intent(this@Users,Choice::class.java)
                    intent.putExtra("User",user.id)
                    startActivity(intent)
                })
                userRecycle.layoutManager=LinearLayoutManager(this@Users)
                userRecycle.adapter=userAdapter
                Toast.makeText(this@Users,"No of items in the result=${userArray.size}",Toast.LENGTH_SHORT).show()
            }
            catch (e:JSONException){
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
            }catch (e:IOException)
            {
                Log.e("Error","Invalid Url/InputStream")
            }
            return res
        }
    }
}
