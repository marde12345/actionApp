package com.mardefasma.influaction_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mardefasma.influaction_java.adapter.InfluencerAdapter;
import com.mardefasma.influaction_java.adapter.PlatformDetailAdapter;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.api.api_res.GetInfById;
import com.mardefasma.influaction_java.api.model.Influencer;
import com.mardefasma.influaction_java.api.model.Platform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailInfActivity extends AppCompatActivity {

    String TAG = "Detail Inf Activity";

    PlatformDetailAdapter platformDetailAdapter;
    RecyclerView rvPlatform;
    ImageView btnBack;
    ApiInterface apiInterface;
    TextView tvNama,tvlokasi;
    ImageView ivprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inf);
        apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Integer sessionId = getIntent().getIntExtra("ID_INF",0);

        btnBack = findViewById(R.id.ivback);
        tvNama = findViewById(R.id.tvname);
        tvlokasi = findViewById(R.id.tvlocation);
        ivprofile = findViewById(R.id.ivprofile);

        Call<GetInfById> getInfByIdCall = apiInterface.getInfById(sessionId);
        getInfByIdCall.enqueue(new Callback<GetInfById>() {
            @Override
            public void onResponse(Call<GetInfById> call, Response<GetInfById> response) {
                Log.d(TAG, "onResponse: "+response.body().getInfluencer().toString());
                Influencer influencer = response.body().getInfluencer();
                updateDetail(influencer);
            }

            @Override
            public void onFailure(Call<GetInfById> call, Throwable t) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void updateDetail(Influencer influencer) {
        tvNama.setText(influencer.getUser().getName());
        tvlokasi.setText(influencer.getUser().getLocation());

        try {
            if (influencer.getUser().getPhoto_profile() != null && influencer.getUser().getPhoto_profile() != "" ) {
                Log.d(TAG, "onBindViewHolder: " + influencer.getUser().getPhoto_profile());
                Picasso.get().load(influencer.getUser().getPhoto_profile()).memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(ivprofile);
            }else {
                Picasso.get().load("https://loremflickr.com/300/400").memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(ivprofile);
            }

        }catch (Exception e){
            Log.d("mbuh", "onBindViewHolder: ");
        };

        setplatformItemRecycler(influencer.getPlatformList());
    }

    private void setplatformItemRecycler(List<Platform> platformList){
        rvPlatform = findViewById(R.id.rv_platform);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvPlatform.setLayoutManager(layoutManager);
        platformDetailAdapter = new PlatformDetailAdapter(this, platformList);
        rvPlatform.setAdapter(platformDetailAdapter);
    }
}