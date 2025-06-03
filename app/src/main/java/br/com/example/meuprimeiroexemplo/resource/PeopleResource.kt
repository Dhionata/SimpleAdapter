package br.com.example.meuprimeiroexemplo.resource

import br.com.example.meuprimeiroexemplo.model.DefaultModel
import br.com.example.meuprimeiroexemplo.model.People
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface PeopleResource {
    @POST("people")
    fun post(@Body people: People): Call<People>
    
    @GET
    fun getNextPage(@Url url: String): Call<DefaultModel>

    @GET("people/{id}")
    operator fun get(@Path("id") id: Int): Call<People>

    @GET("people")
    fun get(): Call<DefaultModel>

    @PUT("people/{id}")
    fun put(@Path("id") id: Int, @Body people: People): Call<Void>

    @PATCH("people/{id}")
    fun patch(@Path("id") id: Int, @Body people: People): Call<Void>

    @DELETE("people/{id}")
    fun delete(@Path("id") id: Int?): Call<Void>
}