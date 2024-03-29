package br.com.example.meuprimeiroexemplo.resource

import br.com.example.meuprimeiroexemplo.model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostResource {
    @POST("posts")
    fun post(@Body post: Post?): Call<Post?>?

    @GET("posts/{id}")
    operator fun get(@Path("id") id: Int?): Call<Post?>?

    @GET("posts")
    fun get(): Call<List<Post?>?>?

    @PUT("posts/{id}")
    fun put(@Path("id") id: Int, @Body post: Post)

    @PATCH("posts/{id}")
    fun patc(@Path("id") id: Int?, @Body post: Post?): Call<Void?>?

    @DELETE("posts/{id}")
    fun delete(@Path("id") id: Int?): Call<Void?>?
}