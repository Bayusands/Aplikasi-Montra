package com.example.aplikasimontra

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SampahAdapter : RecyclerView.Adapter<SampahAdapter.SampahViewHolder>(){
    private var stdList: ArrayList<SampahModel> = ArrayList()
    private var onClickItem: ((SampahModel)-> Unit)? = null
    private var onClickDeleteItem: ((SampahModel)-> Unit)? = null

    fun addItems(items : ArrayList<SampahModel>){
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (SampahModel) -> Unit){
        this.onClickItem = callback
    }

    fun setOnClickDeleteItem(callback: (SampahModel) -> Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SampahViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_std, parent, false)
    )

    override fun getItemCount(): Int {
        return stdList.size
    }

    override fun onBindViewHolder(holder: SampahViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener{ onClickDeleteItem?. invoke(std)}
    }


    class SampahViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvNama)
        private var jenis = view.findViewById<TextView>(R.id.tvJenis)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)

        fun bindView(std: SampahModel){
            id.text = std.id.toString()
            name.text = std.name
            jenis.text = std.jenis
        }
    }
}