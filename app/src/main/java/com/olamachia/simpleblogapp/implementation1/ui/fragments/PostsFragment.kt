package com.olamachia.simpleblogapp.implementation1.ui.fragments

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.databinding.FragmentPostsBinding
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import com.olamachia.simpleblogapp.implementation1.recyclerviewadapters.PostsAdapter
import com.olamachia.simpleblogapp.implementation1.repository.BlogRepository
import com.olamachia.simpleblogapp.implementation1.ui.viewmodels.BlogViewModel
import com.olamachia.simpleblogapp.implementation1.ui.viewmodels.BlogViewModelProviderFactory
import com.olamachia.simpleblogapp.implementation1.util.IRecyclerViewItemClick
import com.olamachia.simpleblogapp.implementation2.view.ui.WelcomeActivity
import java.util.*
import kotlin.collections.ArrayList

class PostsFragment : Fragment(R.layout.fragment_posts) {
   lateinit var binding: FragmentPostsBinding
   private lateinit var viewModel: BlogViewModel
   private lateinit var blogsAdapter: PostsAdapter
   lateinit var dialogFragment: PostDialogFragment
   private lateinit var filteredList: ArrayList<ModelsItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostsBinding.bind(view)

        dialogFragment = PostDialogFragment()

        val blogRepository = BlogRepository()
        var viewModelFactory = BlogViewModelProviderFactory (Application(), blogRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(BlogViewModel::class.java)

        viewModel.getPosts()

        filteredList = arrayListOf()


        viewModel.posts.observe(viewLifecycleOwner, Observer {response ->

            Log.d("refresh", "${response.body()}")//to test if the swipe-refresh gets to call this observation
            if (response.isSuccessful){

                blogsAdapter = PostsAdapter()

                blogsAdapter.differ.submitList(response.body())
                filteredList.addAll(blogsAdapter.differ.currentList)

                binding.postsRecyclerView.adapter = blogsAdapter
                binding.postsRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.postsRecyclerView.setHasFixedSize(true)

                blogsAdapter.setOnItemClickListener(object: IRecyclerViewItemClick{
                    override fun onClick(view: View, position: Int) {
                        //pass data from this fragment to the dialog fragment
                        response.body()?.get(position)?.let { viewModel.changeClickedPost(it.body) }
                        response.body()?.get(position)?.let { viewModel.getPostId(it.id) }
                        //this function launches the comments fragment
                        replaceFragment(PostCommentsFragment())

                    }

                })

            }else{
                Toast.makeText(requireActivity(), "Error occurred", Toast.LENGTH_SHORT).show()
            }
        })

        //set up the recyclerview refresh feature
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            viewModel.getPosts()
            binding.swipeRefresh.isRefreshing = false//stop refreshing
        }


                binding.addPost.setOnClickListener {
                    dialogFragment.show(parentFragmentManager.beginTransaction(), "postDialog")

                }
                binding.implementation2.setOnClickListener {
                    val intent = Intent(requireActivity(), WelcomeActivity::class.java)
                    startActivity(intent)
                }

        binding.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.search.clearFocus()

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotBlank()){

                    filteredList.clear()
                    val searcher  = newText.toLowerCase(Locale.getDefault())
                    blogsAdapter.differ.currentList.forEach {
                       // Toast.makeText(requireActivity(), "$it", Toast.LENGTH_SHORT).show()

                        if (it.body.toLowerCase(Locale.getDefault()).contains(searcher)){
                                filteredList.add(it)

                            blogsAdapter.differ.submitList(filteredList)


                            Log.d("test", "$filteredList")
                        }
                    }

                }else{

                    filteredList.clear()
                    filteredList.addAll(blogsAdapter.differ.currentList)

                }

                return true
            }


        })

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fl_fragment_container, fragment)
        fragmentTransaction?.commit()
        fragmentTransaction?.addToBackStack("")
    }

}