package com.olamachia.simpleblogapp.implementation2.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.implementation2.model.MVCModel
import java.util.*


class MVCComentsFragment : Fragment(R.layout.fragment_m_v_c_coments){
    var commentsModel: MVCModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}