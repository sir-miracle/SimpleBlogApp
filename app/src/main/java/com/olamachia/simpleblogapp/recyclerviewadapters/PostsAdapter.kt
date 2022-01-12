package com.olamachia.simpleblogapp.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.models.ModelsItem
import com.olamachia.simpleblogapp.util.IRecyclerViewItemClick
import kotlinx.android.synthetic.main.post_layout.view.*

class PostsAdapter(): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    var itemClickListener: IRecyclerViewItemClick? = null

    inner class PostsViewHolder(itemView: View, listener: IRecyclerViewItemClick): RecyclerView.ViewHolder(itemView){

    init {
        itemView.setOnClickListener {
            listener.onClick(itemView, adapterPosition)
        }
    }


    }

    private  val differCallback = object: DiffUtil.ItemCallback<ModelsItem>(){
        override fun areItemsTheSame(oldItem: ModelsItem, newItem: ModelsItem): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: ModelsItem, newItem: ModelsItem): Boolean {
            TODO("Not yet implemented")
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view =  LayoutInflater.from(parent.context)
            .inflate(R.layout.post_layout, parent, false)
        return PostsViewHolder(view, itemClickListener!!)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val posts = differ.currentList[position]


        holder.itemView.apply {
            this.rv_posts_textview.text = posts.body
            this.rv_posts_title_textview.text = posts.title
        }

    }

    override fun getItemCount(): Int {
       return  differ.currentList.size
    }

    fun setOnItemClickListener(listener: IRecyclerViewItemClick){
        itemClickListener = listener
    }


}