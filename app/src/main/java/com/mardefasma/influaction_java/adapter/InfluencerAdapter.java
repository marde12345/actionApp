package com.mardefasma.influaction_java.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.model.Influencer;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfluencerAdapter extends RecyclerView.Adapter<InfluencerAdapter.InfluencerViewHolder> {

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

        holder.tvInfluencerName.setText(influencer.getInfluencerName());
        holder.tvInfluencerLocation.setText(influencer.getInfluencerLocation());

        holder.tvInfluencerPrice.setText("Rp. " + influencer.getInfluencerPrice());
        holder.tvInfluencerPrice.setTextColor(context.getResources().getColor(colorTexts[randomNum]));

        holder.ivInfluencerImageBg.setImageResource(bgImgs[randomNum]);
//        holder.ivInfluencerImageBg.setBackground(ContextCompat.getDrawable(context,bgImgs[randomNum]));

        try {
            Picasso.get().load(influencer.getInfluencerImageUrl()).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.ivInfluencerImage);
        }catch (Exception e){
            Log.d("mbuh", "onBindViewHolder: ");
        };

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                holder.childRv.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        layoutManager.setInitialPrefetchItemCount(influencer.getInfluencerIcons().size());
        IconInfluencerAdapter childItemAdapter
                = new IconInfluencerAdapter(
                        context,influencer.getInfluencerIcons());
        holder.childRv.setLayoutManager(layoutManager);
        holder.childRv.setAdapter(childItemAdapter);
        holder.childRv.setRecycledViewPool(viewPool);


//        holder.ivInfluencerImage.setImageResource();

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, Productdetails.class);
///*
//                Pair[] pairs = new Pair[1];
//                pairs[0] = new Pair<View, String>(holder.prodImage, "image");
//                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
//               */ context.startActivity(i/*, activityOptions.toBundle()*/);
//            }
//        });
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
            tvInfluencerPrice = itemView.findViewById(R.id.influencer_price);
            tvInfluencerLocation = itemView.findViewById(R.id.influencer_location);
            tvInfluencerName = itemView.findViewById(R.id.influencer_name);

            ivInfluencerImageBg = itemView.findViewById(R.id.influencer_bg_image);

            childRv = itemView.findViewById(R.id.rv_icon);
        }
    }
}
