package com.ohirunetime.todoapp.api;

import com.ohirunetime.todoapp.model.Counter;
import com.ohirunetime.todoapp.model.Todo;
import com.ohirunetime.todoapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET("alltodo")
    Call<List<Todo>> getAllTodo();


    @GET("user_todo/{uuid}")
    Call<List<Todo>> getUserTodo(@Path("uuid") String uuid);


    @GET("counter")
    Call<Counter> getCounter();


    @POST("counter")
    Call<Counter> counter();


    @FormUrlEncoded
    @POST("user")
    Call<User> saveUser(
            @Field("uuid") String uuid
    );

    @FormUrlEncoded
    @POST("profile")
    Call<User> saveProfile (
            @Field("uuid") String uuid,
            @Field("name") String name
    );


    @FormUrlEncoded
    @POST("save")
    Call<Todo> saveTodo(
            @Field("description") String description,
            @Field("uuid") String uuid
    );

    @FormUrlEncoded
    @POST("update")
    Call<Todo> updateTodo(
            @Field("id") int id

    );

    @FormUrlEncoded
    @POST("delete")
    Call<Todo> delete(
            @Field("id") int id
    );




}
