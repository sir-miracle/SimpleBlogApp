package com.olamachia.simpleblogapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.airbnb.lottie.LottieAnimationView
import com.olamachia.simpleblogapp.R

class SplashScreenActivity : AppCompatActivity() {

    lateinit var lottieAnimationView: LottieAnimationView
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
            //make the splashscreen fill the whole screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        lottieAnimationView = findViewById(R.id.lottie)
        //set the animation
        lottieAnimationView.animate().translationY(1500F).setDuration(1000).setStartDelay(5000)

        handler = Handler(Looper.getMainLooper())
        //you have to delay launching the main activity for a time interval that
        // will allow the animation to play completely,
        // so the delay should be setduration + setstartdelay
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 6000)

    }
}