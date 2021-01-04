package com.mardefasma.influaction_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mardefasma.influaction_java.FilterInfluencerActivity;
import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.api.api_res.GetInf;
import com.mardefasma.influaction_java.api.model.Location;
import com.mardefasma.influaction_java.model.ProductCategory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    String TAG = "Location Adapter";
    Context context;
    List<String> stringList;
    ApiInterface apiInterface;

    public LocationAdapter(Context context, List<String> location) {
        apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        this.context = context;
        this.stringList = location;
    }

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_row_item, parent, false);
        // lets create a recyclerview row item layout file
        return new LocationAdapter.LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+stringList.toString());
        String location = stringList.get(position);

        holder.txtlocation.setText(location);

        holder.txtlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FilterInfluencerActivity.class);
                intent.putExtra("FILTER_NAME", "Terdekat");
                intent.putExtra("FILTER_LOCATION", location);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static final class LocationViewHolder extends RecyclerView.ViewHolder{

        TextView txtlocation;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            txtlocation = itemView.findViewById(R.id.location);

        }
    }
}
