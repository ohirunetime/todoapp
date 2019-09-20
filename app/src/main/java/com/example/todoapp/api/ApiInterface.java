package com.example.todoapp.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.todoapp.model.Todo;
import com.example.todoapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("alltodo")
    Call<List<Todo>> getAllTodo();


    @GET("user_todo/{uuid}")
    Call<List<Todo>> getUserTodo(@Path("uuid") String uuid);



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




}
