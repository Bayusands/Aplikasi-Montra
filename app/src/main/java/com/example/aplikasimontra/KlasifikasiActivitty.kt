package com.example.aplikasimontra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KlasifikasiActivitty : AppCompatActivity() {

    private lateinit var edName : EditText
    private lateinit var edJenis : EditText
    private lateinit var btnAdd : Button
    private lateinit var btnView :Button
    private lateinit var btnUpdate :Button

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter : SampahAdapter? = null
    private var std: SampahModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_klasifikasi_activitty)

        initView()
        initRecyclerView()
        sqLiteHelper = SQLiteHelper(this)

        btnAdd.setOnClickListener{ addSampah() }
        btnView.setOnClickListener{ getSampah() }
        btnUpdate.setOnClickListener{ updateSampah() }

        adapter?.setOnClickItem{
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            edName.setText(it.name)
            edJenis.setText(it.jenis)
            std = it
        }

        adapter?.setOnClickDeleteItem {
            deleteSampah(it.id)
        }
    }

    private fun getSampah() {
        val stdList = sqLiteHelper.getAllSampah()
        Log.e("pppp", "${stdList.size}")

        adapter?.addItems(stdList)
    }

    private fun addSampah() {
        val name = edName.text.toString()
        val jenis = edJenis.text.toString()

        if (name.isEmpty() || jenis.isEmpty()) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else{
            val std = SampahModel(name = name, jenis = jenis)
            val status = sqLiteHelper.insertSampah(std)

            if (status > 1){
                Toast.makeText(this, "Sampah ditambahkan", Toast.LENGTH_SHORT).show()
                clearEditText()
                getSampah()
            } else{
                Toast.makeText(this, "Riwayat disimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateSampah(){
        val name = edName.text.toString()
        val jenis = edJenis.text.toString()

        if (std == null) return

        val std = SampahModel(id = std!!.id, name = name, jenis = jenis)
        val status = sqLiteHelper.updateSampah(std)
        if (status > 1){
            clearEditText()
            getSampah()
        } else{
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteSampah(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Yakin ingin menghapus?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { dialog, _ ->
            sqLiteHelper.deleteSampahById(id)
            getSampah()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText() {
        edName.setText("")
        edJenis.setText("")
        edName.requestFocus()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SampahAdapter()
        recyclerView.adapter = adapter
    }
    private fun initView() {

        edName = findViewById(R.id.edName)
        edJenis = findViewById(R.id.edJenis)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recycleView)

    }
}