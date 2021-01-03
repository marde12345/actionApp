package com.mardefasma.influaction_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mardefasma.influaction_java.DetailInfActivity;
import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.model.Influencer;
import com.mardefasma.influaction_java.api.model.Platform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfluencerAdapter extends RecyclerView.Adapter<InfluencerAdapter.InfluencerViewHolder> {
    String TAG = "Influencer Adapter";

    Context context;
    List<Influencer> influencerList;

    private RecyclerView.RecycledViewPool
            viewPool
            = new RecyclerView
            .RecycledViewPool();

    private static int[] bgImgs = { R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3, R.drawable.bg_4};
    private static int[] colorTexts = { R.color.colorBase1, R.color.colorBase2, R.color.colorBase3, R.color.colorBase4 };

    public InfluencerAdapter(Context context, List<Influencer> influencerList) {
        this.context = context;
        this.influencerList = influencerList;
    }

    @NonNull
    @Override
    public InfluencerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.influencer_row_item,
                        parent,false);
        return new InfluencerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InfluencerViewHolder holder, int position) {
        Random rand = new Random();
        int randomNum = rand.nextInt(4 );

        Influencer influencer
                = influencerList.get(position);

        holder.tvInfluencerName.setText(influencer.getUser().getName());
        holder.tvInfluencerLocation.setText(influencer.getUser().getLocation());

//        holder.tvInfluencerPrice.setText("Rp. " + influencer.getInfluencerPrice());
//        holder.tvInfluencerPrice.setTextColor(context.getResources().getColor(colorTexts[randomNum]));

        holder.ivInfluencerImageBg.setImageResource(bgImgs[randomNum]);
//        holder.ivInfluencerImageBg.setBackground(ContextCompat.getDrawable(context,bgImgs[randomNum]));

        try {
            if (influencer.getUser().getPhoto_profile() != null && influencer.getUser().getPhoto_profile() != "" ) {
                Log.d(TAG, "onBindViewHolder: " + influencer.getUser().getPhoto_profile());
                Picasso.get().load(influencer.getUser().getPhoto_profile()).placeholder(R.drawable.progress_animation).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.ivInfluencerImage);
            }else {
                Picasso.get().load("https://loremflickr.com/300/400").placeholder(R.drawable.progress_animation).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.ivInfluencerImage);
            }

        }catch (Exception e){
            Log.d("mbuh", "onBindViewHolder: ");
        };

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                holder.childRv.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        layoutManager.setInitialPrefetchItemCount(influencer.getPlatformList().size());
        PlatformAdapter childItemAdapter = new PlatformAdapter(context,influencer.getPlatformList());
        holder.childRv.setLayoutManager(layoutManager);
        holder.childRv.setAdapter(childItemAdapter);
        holder.childRv.setRecycledViewPool(viewPool);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailInfActivity.class);
                intent.putExtra("ID_INF", influencer.getUser().getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return influencerList.size();
    }

    public static final class InfluencerViewHolder extends RecyclerView.ViewHolder{
        ImageView ivInfluencerImage, ivInfluencerImageBg;
        TextView tvInfluencerName, tvInfluencerPrice, tvInfluencerLocation;
        RecyclerView childRv;

        public InfluencerViewHolder(@NonNull View itemView) {
            super(itemView);

            ivInfluencerImage = itemView.findViewById(R.id.photoprofileinfluencer);
//            tvInfluencerPrice = itemView.findViewById(R.id.influencer_price);
            tvInfluencerLocation = itemView.findViewById(R.id.influencer_location);
            tvInfluencerName = itemView.findViewById(R.id.influencer_name);

            ivInfluencerImageBg = itemView.findViewById(R.id.influencer_bg_image);

            childRv = itemView.findViewById(R.id.rv_icon);
        }
    }
}
