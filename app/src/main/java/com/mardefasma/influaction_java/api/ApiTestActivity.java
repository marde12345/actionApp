package com.mardefasma.influaction_java.api;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.api_res.GetInf;
import com.mardefasma.influaction_java.api.api_res.GetInfById;
import com.mardefasma.influaction_java.api.model.Influencer;
import com.mardefasma.influaction_java.api.model.Platform;
import com.mardefasma.influaction_java.api.model.User;

public class ApiTestActivity extends AppCompatActivity {
    String TAG = "API_TEST";

    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        Call<GetInf> getInfCall = mApiInterface.getInfluencers();
        getInfCall.enqueue(new Callback<GetInf>() {
            @Override
            public void onResponse(Call<GetInf> call, Response<GetInf> response) {
                Log.d(TAG, "onResponse: "+response.body().getInfluencerList().toString());
            }

            @Override
            public void onFailure(Call<GetInf> call, Throwable t) {

            }
        });
//        Call<GetInfById> getInf = mApiInterface.get(5);
//        getInf.enqueue(new Callback<GetInfById>() {
//            @Override
//            public void onResponse(Call<GetInfById> call, Response<GetInfById> response) {
//                Log.d(TAG, "onResponse: "+response.body().getInfluencer().toString()    );
//            }
//
//            @Override
//            public void onFailure(Call<GetInfById> call, Throwable t) {
//                Log.e(TAG, "onFailure: ",t );
//            }
//        });
//        Call<Platform> storePlat = mApiInterface.storePlatform("tiktok","https://www.tiktok.com/@mardefasma?lang=id-ID",(double)20000,3,100,5);
//        storePlat.enqueue(new Callback<Platform>() {
//            @Override
//            public void onResponse(Call<Platform> call, Response<Platform> response) {
//                Log.d(TAG, "onResponse: "+response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Platform> call, Throwable t) {
//                Log.e(TAG, "onFailure: ",t );
//            }
//        });
//        Call<User> userInf = mApiInterface.getUserById(1);
//        userInf.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.d(TAG, "onResponse: "+response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.e(TAG, "onFailure: ",t );
//            }
//        });

//        Call<List<User>> userInf = mApiInterface.getInfluencer();
//        userInf.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                Log.d(TAG, "onResponse: "+response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Log.e(TAG, "onFailure: ", t);
//            }
//        });
    }
}