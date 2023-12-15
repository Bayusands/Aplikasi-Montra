package com.example.aplikasimontra

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin : Button = findViewById(R.id.btnimp)
        btnLogin.setOnClickListener(this)

        val btnRegis : Button = findViewById(R.id.btnexp)
        btnRegis.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnimp -> {
                val Login = Intent(this@MainActivity, BerandaActivity::class.java)
                startActivity(Login)
            }

            R.id.btnexp -> {
                val url = "https://www.youtube.com/?hl=id&gl=ID"
                val web = Intent(Intent.ACTION_VIEW)
                web.setData(Uri.parse(url))
                startActivity(web)
            }
        }
    }
}