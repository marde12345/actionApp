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

    ImageView profileImageView;
    TextView profileNameTextView;
    ProgressBar progressBar;

    RecyclerView rvInfluencer, productCatRecycler;
    ApiInterface mApiInterface;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_influencer);
        String sessionId = getIntent().getStringExtra("FILTER_NAME");

        mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        utils = new Utils();

        profileImageView = findViewById(R.id.imageView3);
        profileNameTextView = findViewById(R.id.textView5);
        progressBar = findViewById(R.id.rolling);
        final Button signOutButton = findViewById(R.id.logout);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(3, "Instagram"));
        productCategoryList.add(new ProductCategory(4, "Terdekat"));

        setProductRecycler(productCategoryList);

        Call<GetInf> getInfCall = mApiInterface.getInfluencers();
        utils.showDialog(progressBar);
        getInfCall.enqueue(new Callback<GetInf>() {
            @Override
            public void onResponse(Call<GetInf> call, Response<GetInf> response) {
                Log.d(TAG, "onResponse: "+response.body().getInfluencerList().toString());
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
            if (Preferences.getLoggedInUser(getBaseContext())!=null &&
                    !Preferences.getLoggedInUser(getBaseContext()).isEmpty()){
                profileNameTextView.setText("Hei " + Preferences.getLoggedInUser(getBaseContext()) + "!");
            } else{
                profileNameTextView.setText("Hei " + "Anonymous" + "!");
            }
            Picasso.get().load(Uri.parse(Preferences.getKeyPhotoUrl(getBaseContext())))
                    .placeholder(R.drawable.profile)
                    .into(profileImageView);
        }catch (Exception e){
            Log.d("mbuh", "onCreate: asdasd");
        }

        if (!Preferences.getLoggedInStatus(getBaseContext())) signOutButton.setVisibility(View.GONE);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.clearLoggedInUser(getBaseContext());
                signOut();
                startActivity(new Intent(FilterInfluencerActivity.this, LoginActivity.class));
                finish();
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