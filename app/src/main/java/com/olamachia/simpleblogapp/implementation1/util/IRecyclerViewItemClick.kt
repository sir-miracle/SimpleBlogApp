package com.olamachia.simpleblogapp.implementation1.util

import android.view.View

interface IRecyclerViewItemClick {

    fun onClick(view: View, position: Int)
}

interface MVCIRecyclerViewItemClick{
    fun Click(view: View, position: Int)

}