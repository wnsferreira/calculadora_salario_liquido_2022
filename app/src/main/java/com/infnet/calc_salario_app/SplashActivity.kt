package com.infnet.calc_salario_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        lifecycleScope.launch{
            while (true) {
                delay(1000)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
                break
        }}



    }
}