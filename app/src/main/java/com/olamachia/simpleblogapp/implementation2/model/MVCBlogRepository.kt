package com.olamachia.simpleblogapp.implementation2.model

import com.olamachia.simpleblogapp.implementation1.api.RetrofitInstance
import com.olamachia.simpleblogapp.implementation1.models.CommentsModelItem
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import retrofit2.Call
import retrofit2.Response

class MVCBlogRepository {


//     fun getPost(): Call<List<MVCModel>> {
//
//        return MVCRetrofitInstance.api.getPosts()
//    }

     fun getComments(id: Int): Response<List<CommentsModelItem>> {
        return MVCRetrofitInstance.api.getComments(id)
    }

     fun uploadPost(post: ModelsItem): Response<ModelsItem>{
        return MVCRetrofitInstance.api.uploadPost(post)
    }

     fun uploadMyComment(comment: ModelsItem): Response<ModelsItem>{
        return MVCRetrofitInstance.api.postComment(comment)
    }
}