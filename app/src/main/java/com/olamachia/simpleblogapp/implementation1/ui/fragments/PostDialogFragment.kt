package com.olamachia.simpleblogapp.implementation1.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.databinding.FragmentPostDialogBinding
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import com.olamachia.simpleblogapp.implementation1.repository.BlogRepository
import com.olamachia.simpleblogapp.implementation1.ui.viewmodels.BlogViewModel
import com.olamachia.simpleblogapp.implementation1.ui.viewmodels.BlogViewModelProviderFactory

class PostDialogFragment: DialogFragment(R.layout.fragment_post_dialog) {
    lateinit var binding: FragmentPostDialogBinding
    private lateinit var viewModel: BlogViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostDialogBinding.bind(view)

        val blogRepository = BlogRepository()
        var viewModelFactory = BlogViewModelProviderFactory(Application(), blogRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(BlogViewModel::class.java)


        val postTitle = binding.postTitleEditText.text
        val postBody = binding.postbodyEditText.text
        val myPost = ModelsItem(postBody.toString(), 12,postTitle.toString(), 12)

        binding.btnSubmit.setOnClickListener {

            if (validatePost(postTitle.toString(), postBody.toString())){
                viewModel.uploadPost(myPost)
                viewModel.postUploadResponse.observe(viewLifecycleOwner, Observer { uploadResponse ->

                    if (uploadResponse.isSuccessful){
                               binding.postTitleEditText.text!!.clear()
                                binding.postbodyEditText.text!!.clear()
                               Toast.makeText(requireActivity(), "Post uploaded Successfully", Toast.LENGTH_SHORT).show()
                                dismiss()
                           }else{
                                Toast.makeText(requireActivity(), "${uploadResponse.message()}", Toast.LENGTH_SHORT).show()
                                dismiss()
                            }

                    })


                 }else{

                Toast.makeText(requireActivity(), "Either Post Title and/or Post body is empty", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

    }

    private fun validatePost(postTitle: String, thePostItself: String) =
        postTitle.isNotBlank() && thePostItself.isNotBlank()
}