package com.example.aplikasimontra

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.cardview.widget.CardView

class BerandaActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val imageButton : ImageButton = findViewById(R.id.history)
        imageButton.setOnClickListener(this)

        val cardMaps : CardView = findViewById(R.id.cardRecycle)
        cardMaps.setOnClickListener(this)

        val cardMember : CardView = findViewById(R.id.cardSave)
        cardMember.setOnClickListener(this)

        val cardKlasifikasi : CardView = findViewById(R.id.cardBuang)
        cardKlasifikasi.setOnClickListener(this)

        val cardBerita : CardView = findViewById(R.id.cardKomunitas)
        cardBerita.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.history -> {
                val History = Intent(this@BerandaActivity, HistoryActivity::class.java)
                startActivity(History)
            }

            R.id.cardRecycle -> {
                val Maps = Intent(this@BerandaActivity, MapsActivity::class.java)
                startActivity(Maps)
            }

            R.id.cardSave -> {
                val url = "https://www.youtube.com/?hl=id&gl=ID"
                val web = Intent(Intent.ACTION_VIEW)
                web.setData(Uri.parse(url))
                startActivity(web)
            }

            R.id.cardBuang -> {
                val url = "https://www.youtube.com/?hl=id&gl=ID"
                val web = Intent(Intent.ACTION_VIEW)
                web.setData(Uri.parse(url))
                startActivity(web)
            }

            R.id.cardKomunitas -> {
                val url = "https://www.youtube.com/?hl=id&gl=ID"
                val web = Intent(Intent.ACTION_VIEW)
                web.setData(Uri.parse(url))
                startActivity(web)
            }
        }
    }
}