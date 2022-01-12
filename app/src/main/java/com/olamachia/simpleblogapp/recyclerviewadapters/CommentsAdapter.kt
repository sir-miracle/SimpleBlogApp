package com.olamachia.simpleblogapp.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.models.CommentsModelItem
import com.olamachia.simpleblogapp.models.ModelsItem
import kotlinx.android.synthetic.main.comments_layout.view.*

class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    inner class CommentsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


    private  val differCallback = object: DiffUtil.ItemCallback<CommentsModelItem>(){
        override fun areItemsTheSame(oldItem: CommentsModelItem, newItem: CommentsModelItem): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: CommentsModelItem, newItem: CommentsModelItem): Boolean {
            TODO("Not yet implemented")
        }


    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.comments_layout, parent, false))
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comments = differ.currentList[position]

        holder.itemView.apply {
            this.post.text = comments.body
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}