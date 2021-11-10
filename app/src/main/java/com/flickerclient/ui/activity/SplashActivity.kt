package com.flickerclient.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.flickerclient.databinding.ActivitySplashBinding
import com.flickerclient.ui.worker.UpdateWorker

class SplashActivity : AppCompatActivity() {
    private val TAG = "TAG_SPLASH"
    private val SPLASH_TIME_OUT = 2000
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        UpdateWorker.enqueueSelf(this)
        redirectToNextScreen()

    }

    private fun redirectToNextScreen() {
        Handler().postDelayed({
            val i = Intent(this@SplashActivity, HomeActivity::class.java)
            this@SplashActivity.startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}