package com.mardefasma.influaction_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.model.Influencer;
import com.mardefasma.influaction_java.api.model.Platform;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlatformDetailAdapter extends RecyclerView.Adapter<PlatformDetailAdapter.ViewHolder> {

    String TAG = "Platform Adapter";

    Context context;
    List<Platform> platformList;

    public PlatformDetailAdapter(Context context, List<Platform> list) {
        this.context = context;
        this.platformList = list;
    }

    @NonNull
    @Override
    public PlatformDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.platform_row_item,
                        parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatformDetailAdapter.ViewHolder holder, int position) {

        int imageIconUrl;
        String platformName;

        switch (platformList.get(position).getPlatform()){
            case "twitter":
                platformName = "Twitter";
                imageIconUrl = R.drawable.ic_twitter;
                break;
            case "facebook":
                platformName = "Facebook";
                imageIconUrl = R.drawable.ic_facebook;
                break;
            case "instagram":
                platformName = "Instagram";
                imageIconUrl = R.drawable.ic_instagram;
                break;
            case "tiktok":
                platformName = "TikTok";
                imageIconUrl = R.drawable.ic_tik_tok;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + platformList.get(position).getPlatform());
        }

        holder.ivplatform.setImageResource(imageIconUrl);
        holder.tvnama.setText(platformName);
        holder.tvfollower.setText(platformList.get(position).getFollower().toString() + " follower");
        holder.tvprice.setText("Rp. " + platformList.get(position).getPrice().toString());
        holder.tvdays.setText(" / " + platformList.get(position).getPer_days().toString() + " hari");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(platformList.get(position).getUrl()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return platformList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivplatform;
        TextView tvnama, tvfollower, tvprice, tvdays;

        ViewHolder(View itemView)
        {
            super(itemView);
            ivplatform = itemView.findViewById(R.id.ivplatform);
            tvnama = itemView.findViewById(R.id.platform_name);
            tvfollower = itemView.findViewById(R.id.follower);
            tvprice = itemView.findViewById(R.id.price);
            tvdays = itemView.findViewById(R.id.days);
        }
    }
}
