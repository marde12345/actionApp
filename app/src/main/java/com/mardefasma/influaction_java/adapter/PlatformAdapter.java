package com.mardefasma.influaction_java.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.model.Platform;
import com.mardefasma.influaction_java.model.IconInfluencer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlatformAdapter extends RecyclerView.Adapter<PlatformAdapter.ChildViewHolder> {
    String TAG = "Platform Adapter";

    private List<Platform> platformList;
    Context context;

    // Constuctor
    PlatformAdapter(Context context, List<Platform> childItemList)
    {
        this.context = context;
        this.platformList = childItemList;
        Log.d(TAG, "PlatformAdapter: "+ platformList.toString());
    }

    @NonNull
    @Override
    public PlatformAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.icon_influencer_row_item,
                        parent, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatformAdapter.ChildViewHolder holder, int position) {
// Create an instance of the ChildItem
        // class for the given position
        Platform platform
                = platformList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        int imageIconUrl;
        Log.d(TAG, "onBindViewHolder: "+ platformList.get(position).getPlatform());
        switch (platformList.get(position).getPlatform()){
            case "twitter":
                imageIconUrl = R.drawable.ic_twitter;
                break;
            case "facebook":
                imageIconUrl = R.drawable.ic_facebook;
                break;
            case "instagram":
                imageIconUrl = R.drawable.ic_instagram;
                break;
            case "tiktok":
                imageIconUrl = R.drawable.ic_tik_tok;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + platformList.get(position).getPlatform());
        }

        holder.ChildItemImage.setImageResource(imageIconUrl);
    }

    @Override
    public int getItemCount() {
        return platformList.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        ImageView ChildItemImage;

        ChildViewHolder(View itemView)
        {
            super(itemView);
            ChildItemImage
                    = itemView.findViewById(
                    R.id.ivIcon);
        }
    }

}
