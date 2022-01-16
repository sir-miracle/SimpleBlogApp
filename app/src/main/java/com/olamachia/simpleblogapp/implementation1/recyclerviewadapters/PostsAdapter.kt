package com.olamachia.simpleblogapp.implementation1.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import com.olamachia.simpleblogapp.implementation1.util.IRecyclerViewItemClick
import kotlinx.android.synthetic.main.post_layout.view.*

class PostsAdapter(): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    var itemClickListener: IRecyclerViewItemClick? = null// this interface will be used to achieve clicking on the recyclerview

    inner class PostsViewHolder(itemView: View, listener: IRecyclerViewItemClick): RecyclerView.ViewHolder(itemView){

    init {
        //initializing the recyclerview click effect
        itemView.setOnClickListener {
            listener.onClick(itemView, adapterPosition)
        }
    }


    }
        //difutil is more intelligent than normal arraylist, thus it is used
    private  val differCallback = object: DiffUtil.ItemCallback<ModelsItem>(){
        override fun areItemsTheSame(oldItem: ModelsItem, newItem: ModelsItem): Boolean {
           return oldItem.body == newItem.body
        }

        override fun areContentsTheSame(oldItem: ModelsItem, newItem: ModelsItem): Boolean {
            return oldItem == newItem
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