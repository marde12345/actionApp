package com.mardefasma.influaction_java.api;

import com.mardefasma.influaction_java.api.api_res.GetInf;
import com.mardefasma.influaction_java.api.api_res.GetInfById;
import com.mardefasma.influaction_java.api.api_res.LoginUser;
import com.mardefasma.influaction_java.api.model.Influencer;
import com.mardefasma.influaction_java.api.model.Platform;
import com.mardefasma.influaction_java.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("user")
    Call<List<User>> getUsers();

    @FormUrlEncoded
    @POST("user")
    Call<User> getUserById(@Field("id") Integer id);

    @GET("userInf")
//    TODO: buat api influencer
    Call<GetInf> getInfluencers();

    @FormUrlEncoded
    @POST("userInf")
//    TODO: buat api influencer detail id
    Call<GetInfById> getInfById(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("userEmail")
    Call<User> getUserByEmail(@Field("email") String email);

    @FormUrlEncoded
    @POST("login")
//    TODO: buat api login
    Call<LoginUser> loginUser(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
//    TODO: buat api register
    Call<LoginUser> registerUser(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("password") String password,
                                 @Field("password_confirmation") String password_confirmation);

    @FormUrlEncoded
    @POST("platform")
    Call<Platform> storePlatform(@Field("platform") String platform,
                                 @Field("url") String url,
                                 @Field("price") Double price,
                                 @Field("per_days") Integer per_days,
                                 @Field("follower") Integer follower,
                                 @Field("user_id") Integer user_id);

//    TODO: buat api submit endorse
//    TODO: buat api endorsement
//    TODO: buat api endorsement detail id

//    TODO: buat api filter influencernya
}
