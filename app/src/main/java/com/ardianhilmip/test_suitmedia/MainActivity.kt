package com.ardianhilmip.test_suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import com.ardianhilmip.test_suitmedia.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            btnNext.setOnClickListener() {
                val name = etName.text.toString().trim()
                if (name.isEmpty()) {
                    etName.error = getString(R.string.name_is_required)
                    return@setOnClickListener
                } else {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra("name", name)
                    startActivity(intent)
                }
            }

            btnCheck.setOnClickListener() {
                checkPalindrome()
            }
        }
    }

    private fun checkPalindrome() {
        val inputText = binding.etPalindrome.text.toString().trim()
        if (inputText.isEmpty()) {
            binding.etPalindrome.error = getString(R.string.palindrome_is_required)
        } else {
            val isPalindrome = isPalindrom(inputText)
            val message = if (isPalindrome) {
                "isPalindrome"
            } else {
                "not Palindrome"
            }
            showDialog(message)
        }
    }

    private fun isPalindrom(text: String): Boolean {
        val cleanText = text.replace("\\s+".toRegex(), "").toLowerCase(Locale.ROOT)
        val length = cleanText.length
        for (i in 0 until length / 2) {
            if (cleanText[i] != cleanText[length - i - 1]) {
                return false
            }
        }
        return true
    }

    private fun showDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}