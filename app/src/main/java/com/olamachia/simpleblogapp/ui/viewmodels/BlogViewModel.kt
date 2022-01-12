package com.olamachia.simpleblogapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olamachia.simpleblogapp.models.CommentsModelItem
import com.olamachia.simpleblogapp.models.Models
import com.olamachia.simpleblogapp.models.ModelsItem
import com.olamachia.simpleblogapp.repository.BlogRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class BlogViewModel(private val repository: BlogRepository): ViewModel() {

    var clickedPost = " "
    var postId = 0

    fun getPostId(id: Int){
        postId = id
    }

    fun changeClickedPost(post: String){
        clickedPost = post
    }


    fun sendChangedPost() = clickedPost
    fun sendPostId() = postId


    val posts: MutableLiveData<Response<List<ModelsItem>>> = MutableLiveData()
    val comments: MutableLiveData<Response<List<CommentsModelItem>>> = MutableLiveData()
    val postUploadResponse: MutableLiveData<Response<ModelsItem>> = MutableLiveData()
    val myComment: MutableLiveData<Response<ModelsItem>> = MutableLiveData()

    fun getPosts(){
        viewModelScope.launch {
        val response = repository.getPost()
            posts.value = response
        }
    }

    fun getComments(id: Int){
        viewModelScope.launch {

          val commentsResponse = repository.getComments(id)
          comments.value = commentsResponse
        }
    }

    fun uploadPost(post: ModelsItem){
        viewModelScope.launch {
            val uploadResponse: Response<ModelsItem> = repository.uploadPost(post)
            postUploadResponse.value = uploadResponse
        }
    }

    fun uploadMyComment(comment: ModelsItem){
        viewModelScope.launch {
           val commentUpLoadResponse: Response<ModelsItem> = repository.uploadMyComment(comment)
            myComment.value = commentUpLoadResponse

        }
    }
}