package com.olamachia.simpleblogapp.util

import android.view.View

interface IRecyclerViewItemClick {

    fun onClick(view: View, position: Int)
}