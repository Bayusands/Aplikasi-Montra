package com.example.aplikasimontra

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Email
import java.lang.Exception

class SQLiteHelper (context:Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

        companion object{

            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "klasifikasi.db"
            private const val TBL_SAMPAH = "tbl_klasi"
            private const val ID = "id"
            private const val NAME = "name"
            private const val JENIS = "jenis"

        }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblKlasi = ("CREATE TABLE "+ TBL_SAMPAH + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + JENIS + " TEXT" + ")")
        db?.execSQL(createTblKlasi)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("DROP TABLE IF EXISTS $TBL_SAMPAH")
       onCreate(db)
    }

    fun insertSampah(std: SampahModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(JENIS, std.jenis)

        val success = db.insert(TBL_SAMPAH, null, contentValues)
        db.close()
        return success
    }

    fun getAllSampah(): ArrayList<SampahModel>{
        val stdList: ArrayList<SampahModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_SAMPAH"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var jenis: String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                jenis = cursor.getString(cursor.getColumnIndex("jenis"))

                val std = SampahModel(id = id, name = name, jenis = jenis)
                stdList.add(std)
            }while (cursor.moveToNext())
        }

        return stdList
    }

    fun updateSampah(std: SampahModel): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(JENIS, std.jenis)

        val success = db.update(TBL_SAMPAH, contentValues, "id" + std.id, null)
        db.close()
        return success
    }

    fun deleteSampahById(id: Int): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_SAMPAH, "id=$id", null)
        db.close()
        return success
    }
}