package com.mardefasma.influaction_java.data;

import android.util.Log;

import com.mardefasma.influaction_java.Preferences;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.api.api_res.LoginUser;
import com.mardefasma.influaction_java.api.model.User;
import com.mardefasma.influaction_java.data.model.LoggedInUser;

import java.io.IOException;
import java.net.UnknownServiceException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    User userRes;
    LoggedInUser loginedUser;

    public Result<LoggedInUser> login(String username, String password) {
        ApiInterface mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        try {
            // TODO: handle loggedInUser authentication

            Call<LoginUser> userLogin = mApiInterface.loginUser(username, password);
            userLogin.enqueue(new Callback<LoginUser>() {
                @Override
                public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                    if (response.isSuccessful()){
                        User userRes = response.body().getUser();
                        loginedUser = new LoggedInUser(
                                userRes.getId().toString(), userRes.getName()
                        );

                        Log.d("anjing", "onResponse: "+userRes.toString());
                    }else{
                        Log.e("anjing", "onResponse: Apa ini" );
                    }
                }

                @Override
                public void onFailure(Call<LoginUser> call, Throwable t) {
                    Log.e("anjing", "onFailure: ",t );
                }
            });

            Log.d(TAG, "login: "+ loginedUser.toString());
            return new Result.Success<>(loginedUser);

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }

    }

    public void logout() {
        // TODO: revoke authentication
    }
}