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
import com.mardefasma.influaction_java.model.ProductCategory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductViewHolder> {
    String TAG = "Product Category Adapter";
    Context context;
    List<ProductCategory> productCategoryList;
    EditText txt_nama, txt_usia, txt_alamat, txt_status;
    TextView txt_hasil;
    String nama, usia, alamat, status;

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
                    showDialogForm();
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

    private void showDialogForm() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((AppCompatActivity)context).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_data, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Pilih lokasi : ");

        txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
        txt_usia    = (EditText) dialogView.findViewById(R.id.txt_usia);
        txt_alamat  = (EditText) dialogView.findViewById(R.id.txt_alamat);
        txt_status = (EditText) dialogView.findViewById(R.id.txt_status);

        kosong();

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama    = txt_nama.getText().toString();
                usia    = txt_usia.getText().toString();
                alamat  = txt_alamat.getText().toString();
                status = txt_status.getText().toString();

                Log.d(TAG, "onClick: " + "Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // untuk mengosongi edittext
    private void kosong(){
        txt_nama.setText(null);
        txt_usia.setText(null);
        txt_alamat.setText(null);
        txt_status.setText(null);
    }
}
