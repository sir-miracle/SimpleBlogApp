package com.olamachia.simpleblogapp.implementation1.repository

import com.olamachia.simpleblogapp.implementation1.api.RetrofitInstance
import com.olamachia.simpleblogapp.implementation1.models.CommentsModelItem
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import retrofit2.Response

class BlogRepository (

        ) {

    suspend fun getPost(): Response<List<ModelsItem>> {
        return RetrofitInstance.api.getPosts()
    }

    suspend fun getComments(id: Int): Response<List<CommentsModelItem>> {
        return RetrofitInstance.api.getComments(id)
    }

    suspend fun uploadPost(post: ModelsItem): Response<ModelsItem>{
        return RetrofitInstance.api.uploadPost(post)
    }

    suspend fun uploadMyComment(comment: ModelsItem): Response<ModelsItem>{
        return RetrofitInstance.api.postComment(comment)
    }
}