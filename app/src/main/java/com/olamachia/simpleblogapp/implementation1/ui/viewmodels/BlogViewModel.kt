package com.olamachia.simpleblogapp.implementation1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.olamachia.simpleblogapp.implementation1.models.CommentsModelItem
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import com.olamachia.simpleblogapp.implementation1.repository.BlogRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class BlogViewModel(app: Application, private val repository: BlogRepository) : AndroidViewModel(app) {
    var network = com.olamachia.simpleblogapp.implementation1.util.Application()

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
        try{

            viewModelScope.launch {
                val response = repository.getPost()
                posts.value = response
            }

        }catch (e: Exception){

        }

    }

    fun getComments(id: Int){

        try{
            viewModelScope.launch {
                val commentsResponse = repository.getComments(id)
                comments.value = commentsResponse
            }
        }catch (e: Exception){

        }
    }

    fun uploadPost(post: ModelsItem){
       try{
           viewModelScope.launch {
               val uploadResponse: Response<ModelsItem> = repository.uploadPost(post)
               postUploadResponse.value = uploadResponse
           }
       }catch (e: Exception){

       }
    }

    fun uploadMyComment(comment: ModelsItem){
       try{

           viewModelScope.launch {
               val commentUpLoadResponse: Response<ModelsItem> = repository.uploadMyComment(comment)
               myComment.value = commentUpLoadResponse

           }
       }catch (e: Exception){

       }
    }

}