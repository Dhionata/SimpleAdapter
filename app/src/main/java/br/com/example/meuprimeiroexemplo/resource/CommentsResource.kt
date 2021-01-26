package br.com.example.meuprimeiroexemplo.resource

import br.com.example.meuprimeiroexemplo.model.Comments
import retrofit2.Call
import retrofit2.http.*

interface CommentsResource<T, E> {
    @POST("comments")
    fun post(@Body comment: Comments): Call<Comments>

    @GET("comments/{id}")
    operator fun get(@Path("id") id: Int): Call<Comments>

    @GET("comments")
    fun get(): Call<List<Comments>>

    @PUT("comments/{id}")
    fun put(@Path("id") id: Int, @Body comment: Comments): Call<Void>

    @PATCH("comments/{id}")
    fun patc(@Path("id") id: Int, @Body comment: Comments?): Call<Void>

    @DELETE("comments/{id}")
    fun delete(@Path("id") id: Int): Call<Void>
}