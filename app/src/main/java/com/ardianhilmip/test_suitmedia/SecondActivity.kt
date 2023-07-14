package com.ardianhilmip.test_suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.ardianhilmip.test_suitmedia.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            val name = intent.getStringExtra("name")
            if (intent.hasExtra("name")) {
                intent.putExtra("name", name)
                tvName.text = name
            }

            val username = intent.getStringExtra("username")
            if (username != null) {
                binding.tvSelected.text = "Selected $username"
            }

            imgBack.setOnClickListener {
                val intent = Intent(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            btnChoose.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }
}