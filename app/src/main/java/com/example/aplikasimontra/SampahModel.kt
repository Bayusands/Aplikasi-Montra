package com.example.aplikasimontra

import java.util.Random

data class SampahModel (
    var id: Int = getAutoId(),
    var name : String = "",
    var jenis : String = ""
) {
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }

}