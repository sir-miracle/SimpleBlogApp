package com.olamachia.simpleblogapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.databinding.ActivityMainBinding
import com.olamachia.simpleblogapp.ui.fragments.PostsFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(PostsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager  = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragment_container, fragment)
        fragmentTransaction.commit()

    }


}