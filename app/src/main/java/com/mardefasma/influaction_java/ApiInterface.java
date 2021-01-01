package com.mardefasma.influaction_java;

import com.mardefasma.influaction_java.model.LoginResponse;
import com.mardefasma.influaction_java.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("usr")
    Call<List<User>> getUser();

    @FormUrlEncoded
    @POST("userEmail")
    Call<User> getUserByEmail(@Field("email") String email);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> loginUser(@Field("email") String email,
                                  @Field("password") String password);


//    TODO: buat api register
//    TODO: buat api influencer
//    TODO: buat api influencer detail id
//    TODO: buat api login
//    TODO: buat api filter influencernya
//    TODO: buat api submit endorse
//    TODO: buat api endorsement
//    TODO: buat api endorsement detail id
}
