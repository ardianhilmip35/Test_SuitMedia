package com.ardianhilmip.test_suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ardianhilmip.test_suitmedia.adapter.UserAdapter
import com.ardianhilmip.test_suitmedia.data.User
import com.ardianhilmip.test_suitmedia.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    var userList = ArrayList<User>()
    val apiSample = BuildConfig.BASE_URL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            val reqQueue: RequestQueue = Volley.newRequestQueue(this@ThirdActivity)
            val request = JsonObjectRequest(Request.Method.GET,apiSample, null, { res ->
                Log.d("Volley Sample", res.getString("page"))

                val jsonArray = res.getJSONArray("data")
                for (i in 0 until jsonArray.length()){
                    val jsonObj = jsonArray.getJSONObject(i)

                    val user = User(
                        jsonObj.getInt("id"),
                        jsonObj.getString("email"),
                        jsonObj.getString("first_name"),
                        jsonObj.getString("last_name"),
                        jsonObj.getString("avatar")
                    )

                    userList.add(user)
                }

                rvUser.layoutManager = LinearLayoutManager(this@ThirdActivity)
                rvUser.adapter = UserAdapter(userList)

            }, {err ->
                Log.d("Volley Sample Fail", err.message.toString())
            })

            reqQueue.add(request)

            val name = intent.getStringExtra("name")
            tvName.text = name

            imgBack.setOnClickListener {
                val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }

}