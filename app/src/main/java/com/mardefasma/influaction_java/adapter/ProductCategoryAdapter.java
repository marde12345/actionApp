package com.mardefasma.influaction_java.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mardefasma.influaction_java.DetailInfActivity;
import com.mardefasma.influaction_java.FilterInfluencerActivity;
import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.model.ProductCategory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductViewHolder> {

    Context context;
    List<ProductCategory> productCategoryList;
    Dialog dialog;
    LayoutInflater inflater;

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
        holder.catagoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FilterInfluencerActivity.class);
                intent.putExtra("FILTER_NAME", productCategory.getProductName());
                context.startActivity(intent);
            }
        });

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

}
