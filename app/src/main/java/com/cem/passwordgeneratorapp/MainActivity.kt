package com.cem.passwordgeneratorapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cem.passwordgeneratorapp.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initViews
        binding.apply {
            generateButton.setOnClickListener{
                val selectedOptions = mutableListOf<Char>()
                if(checkboxLowercase.isChecked){
                    selectedOptions.addAll(('a'..'z'))
                }
                if(checkboxNumbers.isChecked){
                    selectedOptions.addAll('0'..'9')
                }
                if(checkboxUppercase.isChecked){
                    selectedOptions.addAll(('A'..'Z'))
                }
                if(checkboxSymbols.isChecked){
                    selectedOptions.addAll("!@#$%^&*()_-[]{}|:;,.<>?".toList())
                }
                if(selectedOptions.isEmpty()){
                    Toast.makeText(this@MainActivity,"Please select at least one option.",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val passwordLenght=8
                val randomPassword=buildString{
                    repeat(passwordLenght){
                        val randomIndex= Random.nextInt(0,selectedOptions.size)
                        append(selectedOptions[randomIndex])
                    }
                }
                tvPassword.text=randomPassword
            }
            tvPassword.setOnClickListener{
                val clipboard=getSystemService(Context.CLIPBOARD_SERVICE)as ClipboardManager
                val clip = ClipData.newPlainText("TextViewText",tvPassword.text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this@MainActivity,"copied",Toast.LENGTH_LONG).show()
            }
        }
    }
}