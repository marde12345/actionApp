package com.mardefasma.influaction_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Icon;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mardefasma.influaction_java.adapter.InfluencerAdapter;
import com.mardefasma.influaction_java.adapter.ProductCategoryAdapter;
import com.mardefasma.influaction_java.model.IconInfluencer;
import com.mardefasma.influaction_java.model.Influencer;
import com.mardefasma.influaction_java.model.ProductCategory;
import com.mardefasma.influaction_java.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    InfluencerAdapter influencerAdapter;
    ProductCategoryAdapter productCategoryAdapter;

    ImageView profileImageView;
    TextView profileNameTextView;

    RecyclerView rvInfluencer, productCatRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImageView = findViewById(R.id.imageView3);
        profileNameTextView = findViewById(R.id.textView5);
        final Button signOutButton = findViewById(R.id.logout);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(3, "Instagram"));
        productCategoryList.add(new ProductCategory(4, "Terdekat"));

        setProductRecycler(productCategoryList);


        List<Influencer> influencerList = new ArrayList<>();
        influencerList.add(new Influencer(1,"Marde Fasma","50000", "Madiun","https://loremflickr.com/300/400", ChildIconList1()));
        influencerList.add(new Influencer(2,"Spongebob","100000", "Bikini Bottom","https://loremflickr.com/300/400", ChildIconList2()));
        influencerList.add(new Influencer(3,"Patrick","50000", "Bikini Bottom","https://loremflickr.com/300/400", ChildIconList1()));
        influencerList.add(new Influencer(4,"Olaf","500", "Kapal","https://loremflickr.com/300/400", ChildIconList1()));
        influencerList.add(new Influencer(5,"Flying Dutchman","900000", "Loker Devi John","https://loremflickr.com/300/400", ChildIconList1()));

        setInfluencerItemRecycler(influencerList);

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
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private List<IconInfluencer> ChildIconList1()
    {
        List<IconInfluencer> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new IconInfluencer("Twitter"));
        ChildItemList.add(new IconInfluencer("Facebook"));
        ChildItemList.add(new IconInfluencer("Instagram"));
        ChildItemList.add(new IconInfluencer("TikTok"));

        return ChildItemList;
    }

    private List<IconInfluencer> ChildIconList2()
    {
        List<IconInfluencer> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new IconInfluencer("Twitter"));
        ChildItemList.add(new IconInfluencer("Facebook"));

        return ChildItemList;
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut();
    }

    private void setInfluencerItemRecycler(List<Influencer> influencerList){

        rvInfluencer = findViewById(R.id.influencer_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
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