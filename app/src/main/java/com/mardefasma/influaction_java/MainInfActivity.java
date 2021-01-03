package com.mardefasma.influaction_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
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
import com.mardefasma.influaction_java.adapter.EndorsementAdapter;
import com.mardefasma.influaction_java.adapter.PlatformDetailAdapter;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.api.model.Endorse;
import com.mardefasma.influaction_java.api.model.Platform;
import com.mardefasma.influaction_java.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainInfActivity extends AppCompatActivity {
    String TAG = "MainInf";

    ImageView profileImageView;
    TextView profileNameTextView;

    ApiInterface mApiInterface;

    EndorsementAdapter endorsementAdapter;
    RecyclerView rvEndorse;
    ProgressBar mProgressDialog;
    Utils utils;

    private ArrayList<Endorse> endorses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inf);

        mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        utils = new Utils();

        profileImageView = findViewById(R.id.imageView3);
        profileNameTextView = findViewById(R.id.textView5);
        mProgressDialog = findViewById(R.id.rolling);
        final Button signOutButton = findViewById(R.id.logout);

        try {
            if (Preferences.getLoggedInUser(getBaseContext())!=null &&
                    !Preferences.getLoggedInUser(getBaseContext()).isEmpty()){
                profileNameTextView.setText("Hei " + Preferences.getLoggedInUser(getBaseContext()) + "!");
            } else{
                profileNameTextView.setText("Hei " + "Anonymous" + "!");
            }
            Picasso.get().load(Uri.parse(Preferences.getKeyPhotoUrl(getBaseContext())))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(profileImageView);
        }catch (Exception e){
            Log.d("mbuh", "onCreate: asdasd");
        }

        Call<List<Endorse>> listCall = mApiInterface.getEndorseByInfId(Preferences.getKeyId(getBaseContext()));
        utils.showDialog(mProgressDialog);
        listCall.enqueue(new Callback<List<Endorse>>() {
            @Override
            public void onResponse(Call<List<Endorse>> call, Response<List<Endorse>> response) {
                setEndorseItemRecycler(response.body());
                utils.hideDialog(mProgressDialog);
            }

            @Override
            public void onFailure(Call<List<Endorse>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                utils.hideDialog(mProgressDialog);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.clearLoggedInUser(getBaseContext());
                signOut();
                startActivity(new Intent(MainInfActivity.this, LoginActivity.class));
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

    private void setEndorseItemRecycler(List<Endorse> endorseList){
        rvEndorse = findViewById(R.id.rv_endorsemen);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvEndorse.setLayoutManager(layoutManager);
        endorsementAdapter = new EndorsementAdapter(this, endorseList);
        rvEndorse.setAdapter(endorsementAdapter);
    }
}