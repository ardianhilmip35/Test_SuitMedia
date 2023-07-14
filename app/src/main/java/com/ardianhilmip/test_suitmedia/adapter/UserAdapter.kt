package com.ardianhilmip.test_suitmedia.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.ardianhilmip.test_suitmedia.R
import com.ardianhilmip.test_suitmedia.SecondActivity
import com.ardianhilmip.test_suitmedia.data.User
import com.bumptech.glide.Glide

class UserAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile = itemView.findViewById(R.id.tv_item_photo) as ImageView
        val name = itemView.findViewById(R.id.tv_item_username) as TextView
        val email = itemView.findViewById(R.id.tv_item_email) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        Glide.with(context)
            .load(user.avatar)
            .into(holder.profile)

        val stringBuilder = StringBuilder()
        stringBuilder.append(user.first_name).append(" ").append(user.last_name)

        holder.name.text = stringBuilder.toString()
        holder.email.text = user.email

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("username", stringBuilder.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = userList.size

}