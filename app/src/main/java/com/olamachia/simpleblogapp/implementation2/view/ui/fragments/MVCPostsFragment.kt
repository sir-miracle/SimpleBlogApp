package com.olamachia.simpleblogapp.implementation2.view.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.databinding.FragmentMVCPostsBinding
import com.olamachia.simpleblogapp.databinding.FragmentPostsBinding
import com.olamachia.simpleblogapp.implementation1.api.RetrofitInstance
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import com.olamachia.simpleblogapp.implementation1.recyclerviewadapters.PostsAdapter
import com.olamachia.simpleblogapp.implementation1.repository.BlogRepository
import com.olamachia.simpleblogapp.implementation1.util.IRecyclerViewItemClick
import com.olamachia.simpleblogapp.implementation1.util.MVCIRecyclerViewItemClick
import com.olamachia.simpleblogapp.implementation2.model.IMVCBlogAPI
import com.olamachia.simpleblogapp.implementation2.model.MVCBlogRepository
import com.olamachia.simpleblogapp.implementation2.model.MVCModel
import com.olamachia.simpleblogapp.implementation2.model.MVCRetrofitInstance
import com.olamachia.simpleblogapp.implementation2.view.ui.adapters.MVCPostsadapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

import java.util.*


class MVCPostsFragment :Fragment() {
    var retrofit: IMVCBlogAPI = MVCRetrofitInstance.api
    private var compositeDisposable = CompositeDisposable()

    lateinit var binding: FragmentMVCPostsBinding
    private lateinit var blogAdapter: MVCPostsadapter




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_m_v_c_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMVCPostsBinding.bind(view)
        blogAdapter = MVCPostsadapter()

            posts()

    }


    fun posts(){
        compositeDisposable.add(retrofit.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe{

                blogAdapter = MVCPostsadapter()
                blogAdapter.differ.submitList(it)

                binding.mvcPostsRecyclerView.adapter = blogAdapter
                binding.mvcPostsRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.mvcPostsRecyclerView.setHasFixedSize(true)
            }
        )

    }




}