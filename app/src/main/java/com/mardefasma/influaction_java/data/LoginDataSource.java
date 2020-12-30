package com.mardefasma.influaction_java.data;

import android.util.Log;

import com.mardefasma.influaction_java.data.model.LoggedInUser;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            if (username.equals("qwe") && password.equals("123456")){
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                "Marde Fasma");
                return new Result.Success<>(fakeUser);
            }else {
                return new Result.Error(new IOException("Wrong"));
            }

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }

    }

    public void logout() {
        // TODO: revoke authentication
    }
}