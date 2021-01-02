package com.mardefasma.influaction_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

public class MainInfActivity extends AppCompatActivity {
    String TAG = "MainInf";

    ImageView profileImageView;
    TextView profileNameTextView;

    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inf);

        mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        profileImageView = findViewById(R.id.imageView3);
        profileNameTextView = findViewById(R.id.textView5);
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
}