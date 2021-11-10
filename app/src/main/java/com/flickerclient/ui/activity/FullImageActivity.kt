package com.flickerclient.ui.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.flickerclient.databinding.ActivityFullImageBinding
import com.squareup.picasso.Picasso
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class FullImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityFullImageBinding
    val IMAGE_URL="imageURL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        if (intent.hasExtra(IMAGE_URL)) {
            var imageUrl  = intent.getStringExtra(IMAGE_URL)
            var bitmap:Bitmap?= getBitmapFromURL(imageUrl)
            binding.ivIamge.setImageBitmap(bitmap)

        }
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            null
        }
    }
}