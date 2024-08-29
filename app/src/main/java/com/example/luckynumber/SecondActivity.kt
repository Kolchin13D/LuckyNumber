package com.example.luckynumber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val share: Button = findViewById(R.id.shareBtn)

        var name = receiveName()
        var random = generateNumber()

        val text2: TextView = findViewById(R.id.textView2)
        text2.setText("$name, your lucky numer is: \n $random")

        share.setOnClickListener(){
            shareData(name, random)
        }
    }

    fun receiveName(): String {
        val bundle: Bundle? = intent.extras
        val name = bundle?.getString("name").toString()
        return name
    }

    fun generateNumber(): Int {
        var luckyNumber = Random.nextInt(5)
        return luckyNumber
    }

    fun shareData(name: String, num: Int) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "$name is lucky")
        intent.putExtra(Intent.EXTRA_TEXT, "My lucky number is: $num")
        startActivity(intent)
    }
}