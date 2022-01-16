package com.olamachia.simpleblogapp.implementation1.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.databinding.FragmentPostCommentsBinding
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import com.olamachia.simpleblogapp.implementation1.recyclerviewadapters.CommentsAdapter
import com.olamachia.simpleblogapp.implementation1.repository.BlogRepository
import com.olamachia.simpleblogapp.implementation1.ui.viewmodels.BlogViewModel
import com.olamachia.simpleblogapp.implementation1.ui.viewmodels.BlogViewModelProviderFactory


class PostCommentsFragment : Fragment(R.layout.fragment_post_comments) {

    lateinit var binding: FragmentPostCommentsBinding
    private lateinit var viewModel: BlogViewModel
    lateinit var commentsAdapter: CommentsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostCommentsBinding.bind(view)
        //instantiate the blogrepository and viewmodelfactory
        val blogRepository = BlogRepository()
        val viewModelFactory = BlogViewModelProviderFactory(Application(), blogRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(BlogViewModel::class.java)

        binding.post.text = "${viewModel.sendChangedPost()}"

        viewModel.getComments(viewModel.sendPostId())

        viewModel.comments.observe(viewLifecycleOwner, Observer { commentsResponse ->

            if (commentsResponse.isSuccessful){
                //set up the recyclerview
                commentsAdapter = CommentsAdapter()
                commentsAdapter.differ.submitList(commentsResponse.body())
                binding.commentsRecyclerview.adapter = commentsAdapter
                binding.commentsRecyclerview.layoutManager = LinearLayoutManager(activity)
                binding.commentsRecyclerview.setHasFixedSize(true)

            }else{

                Toast.makeText(requireActivity(), "An Error has occured", Toast.LENGTH_SHORT).show()
            }

        })

        val myComment = binding.enterComment.text
        val commentId = viewModel.sendPostId()
        val comment = ModelsItem(myComment.toString(),commentId,"",12)
        binding.sendComment.setOnClickListener {

            if (validateComment(myComment.toString())){

                viewModel.uploadMyComment(comment)

                viewModel.myComment.observe(viewLifecycleOwner, Observer { commentResponse ->

                    if (commentResponse.isSuccessful){

                        binding.enterComment.text.clear()
                        Toast.makeText(requireActivity(), "Comment uploaded successfully", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(requireActivity(), "${commentResponse.message()}", Toast.LENGTH_SHORT).show()

                    }


                })

            }else{
                Toast.makeText(requireActivity(), "Comment field cannot be empty", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun validateComment(theComment: String) = theComment.isNotBlank()

}