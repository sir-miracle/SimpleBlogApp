package com.olamachia.simpleblogapp.ui.fragments

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
import com.olamachia.simpleblogapp.models.Models
import com.olamachia.simpleblogapp.models.ModelsItem
import com.olamachia.simpleblogapp.recyclerviewadapters.PostsAdapter
import com.olamachia.simpleblogapp.repository.BlogRepository
import com.olamachia.simpleblogapp.ui.viewmodels.BlogViewModel
import com.olamachia.simpleblogapp.ui.viewmodels.BlogViewModelProviderFactory
import com.olamachia.simpleblogapp.util.IRecyclerViewItemClick
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
        var viewModelFactory = BlogViewModelProviderFactory(blogRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(BlogViewModel::class.java)

        viewModel.getPosts()

        filteredList = arrayListOf()

        viewModel.posts.observe(viewLifecycleOwner, Observer {response ->

            if (response.isSuccessful){

                blogsAdapter = PostsAdapter()
                blogsAdapter.differ.submitList(response.body())

                filteredList.addAll(blogsAdapter.differ.currentList)

                binding.postsRecyclerView.adapter = blogsAdapter
                binding.postsRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.postsRecyclerView.setHasFixedSize(true)

                blogsAdapter.setOnItemClickListener(object: IRecyclerViewItemClick{
                    override fun onClick(view: View, position: Int) {

                        response.body()?.get(position)?.let { viewModel.changeClickedPost(it.body) }
                        response.body()?.get(position)?.let { viewModel.getPostId(it.id) }

                        replaceFragment(PostCommentsFragment())

                    }

                })

            }else{
                Toast.makeText(requireActivity(), "Error occurred", Toast.LENGTH_SHORT).show()
            }
        })


                binding.addPost.setOnClickListener {
                    dialogFragment.show(parentFragmentManager.beginTransaction(), "postDialog")

                }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                 filteredList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())

                if (searchText.isNotBlank()){

                    filteredList.forEach{

                        if (it.body.toLowerCase(Locale.getDefault()).contains(searchText)){

                            filteredList.add(it)
                        }
                    }
                    //supposed to do 'notifyDataSetChanged' here.
                // but because I am using difUtil, it happens automatically

                }else{

                    filteredList.clear()
                    filteredList.addAll(blogsAdapter.differ.currentList)

                }



                return false
            }


        })



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fl_fragment_container, fragment)
        fragmentTransaction?.commit()
        fragmentTransaction?.addToBackStack("")
    }

}