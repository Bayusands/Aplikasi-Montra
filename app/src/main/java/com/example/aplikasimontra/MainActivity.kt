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

        val btnMasuk : Button = findViewById(R.id.btnin)
        btnMasuk.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnin -> {
                val Masuk = Intent(this@MainActivity, BerandaActivity::class.java)
                startActivity(Masuk)
            }
        }
    }
}