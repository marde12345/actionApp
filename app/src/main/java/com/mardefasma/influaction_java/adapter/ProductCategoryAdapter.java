package com.mardefasma.influaction_java.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mardefasma.influaction_java.DetailInfActivity;
import com.mardefasma.influaction_java.FilterInfluencerActivity;
import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.api.model.Platform;
import com.mardefasma.influaction_java.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductViewHolder> {
    String TAG = "Product Category Adapter";
    Context context;
    List<ProductCategory> productCategoryList;
    private RecyclerView.RecycledViewPool
            viewPool
            = new RecyclerView
            .RecycledViewPool();
    ApiInterface mApiInterface;

    public ProductCategoryAdapter(Context context, List<ProductCategory> productCategoryList) {
        this.context = context;
        this.productCategoryList = productCategoryList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_row_item, parent, false);
        // lets create a recyclerview row item layout file
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductCategory productCategory = productCategoryList.get(position);

        holder.catagoryName.setText(productCategory.getProductName());

        if (productCategory.getProductName().equals("Terdekat")){
            holder.catagoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialogForm(holder);
                }
            });
        }else{
            holder.catagoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FilterInfluencerActivity.class);
                    intent.putExtra("FILTER_NAME", productCategory.getProductName());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return productCategoryList.size();
    }


    public static final class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView catagoryName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            catagoryName = itemView.findViewById(R.id.cat_name);

        }
    }

    private void showDialogForm(@NonNull ProductViewHolder holder) {
        RecyclerView rvLocation;
        mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((AppCompatActivity)context).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_data, null);
        dialog.setCancelable(true);
        dialog.setTitle("Pilih lokasi : ");

        rvLocation = dialogView.findViewById(R.id.rv_location);

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        List<String> stringList = new ArrayList<>();
        Call<List<String>> listCall = mApiInterface.getFilterKota();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.d(TAG, "onResponse: "+response.body().toString());
                List<String> namaKota = response.body();
                setLocationItemRecycler(rvLocation, namaKota, dialogView);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

        dialog.setView(dialogView);
        dialog.show();
    }

    private void setLocationItemRecycler(RecyclerView rvLocation, List<String> stringList, View dialogView){
        Log.d(TAG, "setLocationItemRecycler: "+stringList.toString());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rvLocation.setLayoutManager(layoutManager);
        LocationAdapter locationAdapter = new LocationAdapter(dialogView.getContext(), stringList);
        Log.d(TAG, "setLocationItemRecycler: "+locationAdapter.getItemCount());
        rvLocation.setAdapter(locationAdapter);
//        rvLocation.setRecycledViewPool(viewPool);
    }
}
