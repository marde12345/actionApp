package com.mardefasma.influaction_java;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

public class Preferences {
    /** Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.
     * Jadi setiap data mempunyai key yang berbeda satu sama lain */
    static final String KEY_PHOTO_URL = "Photo_url";
    static final String KEY_ID_GOOGLE = "ID_GOOGLE";
    static final String KEY_ACCOUNT = "Acc";
    static final String KEY_ROLE = "ROle";
    static final String KEY_ID = "ID";
    static final String KEY_USER_TEREGISTER ="user", KEY_PASS_TEREGISTER ="pass";
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";

    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context */
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key isi KEY_USER_TEREGISTER dengan parameter username */
    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TEREGISTER, username);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USER_TEREGISTER berupa String */
    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_PASS_TEREGISTER dengan parameter password */
    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_TEREGISTER, password);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_PASS_TEREGISTER berupa String */
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username */
    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String */
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_STATUS_SEDANG_LOGIN dengan parameter status */
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN,status);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean */
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN,false);
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_PHOTO_URL dengan parameter status */
    public static void setKeyPhotoUrl(Context context, String url){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PHOTO_URL,url);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean */
    public static String getKeyPhotoUrl(Context context){
        return getSharedPreference(context).getString(KEY_PHOTO_URL,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_PHOTO_URL dengan parameter status */
    public static void setKeyAccount(Context context, String json){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ACCOUNT,json);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean */
    public static String getKeyAccount(Context context){
        return getSharedPreference(context).getString(KEY_ACCOUNT,"");
    }

    public static void setKeyIdGoogle(Context context, String id){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ID_GOOGLE,id);
        editor.apply();
    }
    public static String getKeyIdGoogle(Context context){
        return getSharedPreference(context).getString(KEY_ID_GOOGLE,"");
    }

    public static void setKeyRole(Context context, String role){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ROLE,role);
        editor.apply();
    }
    public static String getKeyRole(Context context){
        return getSharedPreference(context).getString(KEY_ROLE,"");
    }

    public static void setKeyId(Context context, String id){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ID,id);
        editor.apply();
    }
    public static String getKeyId(Context context){
        return getSharedPreference(context).getString(KEY_ID,"");
    }

    public static String convertGsaToGson(GoogleSignInAccount gsa){
        Gson gson = new Gson();
        String json = gson.toJson(gsa);

        return json;
    }

    public static GoogleSignInAccount convertGsonToGsa(String json){
        Gson gson = new Gson();
        GoogleSignInAccount gsa = gson.fromJson(json, GoogleSignInAccount.class);

        return gsa;
    }

    /** Deklarasi Edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
     *  khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_STATUS_SEDANG_LOGIN */
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.remove(KEY_PHOTO_URL);
        editor.remove(KEY_ROLE);
        editor.remove(KEY_ID_GOOGLE);
        editor.remove(KEY_ID);
        editor.apply();
    }
}
