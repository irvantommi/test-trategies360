package com.example.irvantest.rest.login;

import com.example.irvantest.model.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiLogin {
    @GET("login")
    Call<User> getUser();

    @FormUrlEncoded
    @POST("auth")
    Call<JsonObject> login(@Field("username") String username,
                           @Field("password") String password);

    @FormUrlEncoded
    @PUT("update")
    Call<User> updateUser(@Field("id") String id,
                          @Field("username") String username,
                          @Field("password") String password,
                          @Field("firstName") String firstName,
                          @Field("lastName") String lastName);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "user", hasBody = true)
    Call<User> deleteUser(@Field("id") String id);
}