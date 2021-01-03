package com.mardefasma.influaction_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mardefasma.influaction_java.adapter.InfluencerAdapter;
import com.mardefasma.influaction_java.adapter.ProductCategoryAdapter;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.api.api_res.GetInf;
import com.mardefasma.influaction_java.api.model.Influencer;
import com.mardefasma.influaction_java.model.IconInfluencer;
import com.mardefasma.influaction_java.model.ProductCategory;
import com.mardefasma.influaction_java.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilterInfluencerActivity extends AppCompatActivity {

    String TAG = "Filter";

    InfluencerAdapter influencerAdapter;
    ProductCategoryAdapter productCategoryAdapter;

    ImageView profileImageView, btnBack;
    TextView profileNameTextView;
    ProgressBar progressBar;

    RecyclerView rvInfluencer, productCatRecycler;
    ApiInterface mApiInterface;
    Utils utils;
    Call<GetInf> getInfCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_influencer);
        String sessionFilter = getIntent().getStringExtra("FILTER_NAME");
        Log.d(TAG, "onCreate: "+sessionFilter);

        mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        utils = new Utils();

        profileImageView = findViewById(R.id.imageView3);
        profileNameTextView = findViewById(R.id.textView5);
        progressBar = findViewById(R.id.rolling);
        btnBack = findViewById(R.id.ivback);
        final Button signOutButton = findViewById(R.id.logout);

        profileNameTextView.setText(sessionFilter);

        switch (sessionFilter){
            case "Instagram":
                getInfCall = mApiInterface.getFilterInstagram();
                break;
            case "Trending":
                getInfCall = mApiInterface.getFilterTrending();
                break;
            case "Terdekat":
                getInfCall = mApiInterface.getFilterTerdekat();
                break;
            case "Populer":
                getInfCall = mApiInterface.getFilterPopuler();
                break;
            default:
                getInfCall = mApiInterface.getInfluencers();
                break;
        }

        utils.showDialog(progressBar);
        getInfCall.enqueue(new Callback<GetInf>() {
            @Override
            public void onResponse(Call<GetInf> call, Response<GetInf> response) {
                List<Influencer> influencerList = response.body().getInfluencerList();
                if (influencerList.size()>0) {
                    setInfluencerItemRecycler(influencerList);
                    utils.hideDialog(progressBar);
                }
            }

            @Override
            public void onFailure(Call<GetInf> call, Throwable t) {
//                Log.e(TAG, "onFailure: ",t );
                utils.hideDialog(progressBar);
            }
        });

        try {
            Picasso.get().load(Uri.parse(Preferences.getKeyPhotoUrl(getBaseContext())))
                    .placeholder(R.drawable.profile)
                    .into(profileImageView);
        }catch (Exception e){
            Log.d("mbuh", "onCreate: asdasd");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut();
    }

    private void setInfluencerItemRecycler(List<Influencer> influencerList){

        rvInfluencer = findViewById(R.id.influencer_small_recycler);
//        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rvInfluencer.setLayoutManager(layoutManager);
        influencerAdapter = new InfluencerAdapter(this, influencerList);
        rvInfluencer.setAdapter(influencerAdapter);

    }

    private void setProductRecycler(List<ProductCategory> productCategoryList){

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);

    }

}