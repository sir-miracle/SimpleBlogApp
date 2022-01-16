package com.olamachia.simpleblogapp.implementation2.model

import com.olamachia.simpleblogapp.implementation1.models.CommentsModelItem
import com.olamachia.simpleblogapp.implementation1.models.ModelsItem
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IMVCBlogAPI {

    @GET("/posts")
     fun getPosts(): Observable<List<MVCModel>>

    @GET("/posts/{id}/comments")
     fun getComments(@Path("id")id: Int): Response<List<CommentsModelItem>>

    @POST("/posts/{id}/comments")
     fun postComment(@Path("id")comment: ModelsItem): Response<ModelsItem>

    @POST("/posts")
     fun uploadPost(@Body post: ModelsItem): Response<ModelsItem>

}