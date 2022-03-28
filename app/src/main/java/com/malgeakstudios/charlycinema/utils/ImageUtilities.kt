package com.malgeakstudios.charlycinema.utils

import android.content.Context
import java.io.IOException

import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL
import android.graphics.Bitmap
import android.util.Base64
import java.io.FileOutputStream

import java.io.ByteArrayOutputStream
import java.lang.Exception



object ImageUtilities {
    fun saveImageB64(name: String, image: String, context: Context){
        var fileOutputStream: FileOutputStream? = null
        try{
            fileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE)
            fileOutputStream.write(image.toByteArray())
        }catch (e : IOException){
            e.printStackTrace()
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close()
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    fun descargarImagen(imageHttpAddress: String): Bitmap? {
        var imageUrl: URL? = null
        var imagen: Bitmap? = null
        try {
            imageUrl = URL(imageHttpAddress)
            val conn = imageUrl.openConnection() as HttpURLConnection
            conn.connect()
            imagen = BitmapFactory.decodeStream(conn.inputStream)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return imagen
    }

    fun encodeImage(bm: Bitmap, typeFile:String): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}