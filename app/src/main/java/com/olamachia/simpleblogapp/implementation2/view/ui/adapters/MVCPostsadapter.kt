package com.olamachia.simpleblogapp.implementation2.view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.implementation1.util.MVCIRecyclerViewItemClick
import com.olamachia.simpleblogapp.implementation2.model.MVCModel
import kotlinx.android.synthetic.main.mvc_posts_layout.view.*


class MVCPostsadapter: RecyclerView.Adapter<MVCPostsadapter.BlogsViewHolder>() {

    var ClickListener: MVCIRecyclerViewItemClick? = null

    inner class BlogsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    private  val differCallback = object: DiffUtil.ItemCallback<MVCModel>(){
        override fun areItemsTheSame(oldItem: MVCModel, newItem: MVCModel): Boolean {
            return oldItem.body == newItem.body
        }

        override fun areContentsTheSame(oldItem: MVCModel, newItem: MVCModel): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogsViewHolder {
        val view =  LayoutInflater.from(parent.context)
            .inflate(R.layout.mvc_posts_layout, parent, false)
        return BlogsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogsViewHolder, position: Int) {
        val posts = differ.currentList[position]


        holder.itemView.apply {
            this.mvc_rv_posts_textview.text = posts.body
            this.mvc_rv_posts_title_textview.text = posts.title
        }

    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    fun setOnItemClickListener(listener: MVCIRecyclerViewItemClick){
        ClickListener = listener
    }


}