package com.mardefasma.influaction_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.model.IconInfluencer;
import com.mardefasma.influaction_java.picasso.PicassoTrustAll;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IconInfluencerAdapter extends RecyclerView.Adapter<IconInfluencerAdapter.ChildViewHolder> {

    private List<IconInfluencer> ChildItemList;
    Context context;

    // Constuctor
    IconInfluencerAdapter(Context context, List<IconInfluencer> childItemList)
    {
        this.context = context;
        this.ChildItemList = childItemList;
    }

    @NonNull
    @Override
    public IconInfluencerAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Here we inflate the corresponding
        // layout of the child item
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.icon_influencer_row_item,
                        parent, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconInfluencerAdapter.ChildViewHolder holder, int position) {
        // Create an instance of the ChildItem
        // class for the given position
        IconInfluencer childItem
                = ChildItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        int imageIconUrl;

        switch (ChildItemList.get(position).getInfluencerIcon()){
            case "Twitter":
                imageIconUrl = R.drawable.ic_twitter;
                break;
            case "Facebook":
                imageIconUrl = R.drawable.ic_facebook;
                break;
            case "Instagram":
                imageIconUrl = R.drawable.ic_instagram;
                break;
            case "TikTok":
                imageIconUrl = R.drawable.ic_tik_tok;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + ChildItemList.get(position).getInfluencerIcon());
        }

        holder.ChildItemImage.setImageResource(imageIconUrl);

//        imageIconUrl = "https://loremflickr.com/300/400";
//        Picasso.get().load(imageIconUrl).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.mipmap.ic_launcher).into(holder.ChildItemImage);
//        PicassoTrustAll.getInstance(context).load(imageIconUrl).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.mipmap.ic_launcher).into(holder.ChildItemImage);
    }

    @Override
    public int getItemCount() {
        return ChildItemList.size();
    }

    class ChildViewHolder
            extends RecyclerView.ViewHolder {

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
