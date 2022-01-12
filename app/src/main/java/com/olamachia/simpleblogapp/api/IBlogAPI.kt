package com.olamachia.simpleblogapp.api

import com.olamachia.simpleblogapp.models.CommentsModelItem
import com.olamachia.simpleblogapp.models.Models
import com.olamachia.simpleblogapp.models.ModelsItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IBlogAPI {

    @GET("/posts")
    suspend fun getPosts(): Response<List<ModelsItem>>

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id")id: Int): Response<List<CommentsModelItem>>

    @POST("/posts/{id}/comments")
    suspend fun postComment(@Path("id")comment: ModelsItem): Response<ModelsItem>

    @POST("/posts")
    suspend fun uploadPost(@Body post: ModelsItem): Response<ModelsItem>

}