package com.olamachia.simpleblogapp.implementation2.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.implementation2.view.ui.fragments.MVCPostsFragment

class WelcomeActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar!!.hide()

        handler = Handler(Looper.getMainLooper())

        handler.postDelayed({

            val intent = Intent(this, MVCHomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }

}