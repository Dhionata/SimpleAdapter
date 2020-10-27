package br.com.example.meuprimeiroexemplo.resource;

import java.util.List;

import br.com.example.meuprimeiroexemplo.model.Comments;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentsResource<T, E> {

    @POST("comments")
    Call<Comments> post(@Body Comments comment);

    @GET("comments/{id}")
    Call<Comments> get(@Path("id") Integer id);

    @GET("comments")
    Call<List<Comments>> get();

    @PUT("comments/{id}")
    Call<Void> put(@Path("id") Integer id, @Body Comments comment);

    @PATCH("comments/{id}")
    Call<Void> patc(@Path("id") Integer id, @Body Comments comment);

    @DELETE("comments/{id}")
    Call<Void> delete(@Path("id") Integer id);
}