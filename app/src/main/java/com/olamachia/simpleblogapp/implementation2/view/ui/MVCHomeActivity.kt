package com.olamachia.simpleblogapp.implementation2.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.implementation1.ui.fragments.PostsFragment
import com.olamachia.simpleblogapp.implementation2.view.ui.fragments.MVCPostsFragment

class MVCHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvchome)

        replaceFragment(MVCPostsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager  = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragment_container_mvc, fragment)
        fragmentTransaction.commit()

    }
}