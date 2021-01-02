package com.mardefasma.influaction_java.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.model.Endorse;
import com.mardefasma.influaction_java.api.model.Platform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EndorsementAdapter extends RecyclerView.Adapter<EndorsementAdapter.ViewHolder> {
    String TAG = "Endorsement Adapter";

    Context context;
    List<Endorse> endorseList;

    public EndorsementAdapter(Context context, List<Endorse> list) {
        this.context = context;
        this.endorseList = list;
    }

    @NonNull
    @Override
    public EndorsementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.endorse_row_item,
                        parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EndorsementAdapter.ViewHolder holder, int position) {
        Endorse endorse = endorseList.get(position);

        try {
            if (endorse.getCust().getPhoto_profile() != null && endorse.getCust().getPhoto_profile() != "" ) {
                Picasso.get().load(endorse.getCust().getPhoto_profile()).placeholder(R.drawable.profile).memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(holder.ivcust);
            }else {
                Picasso.get().load("https://loremflickr.com/300/400").placeholder(R.drawable.profile).memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(holder.ivcust);
            }

        }catch (Exception e){
            Log.d("mbuh", "onBindViewHolder: ");
        };

        holder.tvnamacust.setText(endorse.getCust().getName());
        holder.begin.setText(endorse.getBegin().toString() + " s/d ");
        holder.end.setText(endorse.getEnd().toString());
    }

    @Override
    public int getItemCount() {
        return endorseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivcust;
        TextView tvnamacust, begin, end;

        ViewHolder(View itemView)
        {
            super(itemView);
            ivcust = itemView.findViewById(R.id.ivplatform);
            tvnamacust= itemView.findViewById(R.id.tvcust);
            begin = itemView.findViewById(R.id.begin);
            end = itemView.findViewById(R.id.end);
        }
    }
}
