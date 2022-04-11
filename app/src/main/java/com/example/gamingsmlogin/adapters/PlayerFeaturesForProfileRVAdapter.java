package com.example.gamingsmlogin.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.classes.PlayerFeaturesForProfile;
import com.example.gamingsmlogin.classes.PlayerFeaturesForProfileDialog;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerFeaturesForProfileRVAdapter extends RecyclerView.Adapter<PlayerFeaturesForProfileRVAdapter.PlayerFeaturesHolder> {

    Context context;
    Activity activity;
    List<PlayerFeaturesForProfile> pfList;

    public PlayerFeaturesForProfileRVAdapter(Activity activity, Context context, List<PlayerFeaturesForProfile> pfList) {
        this.context = context;
        this.pfList = pfList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PlayerFeaturesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_playerfeatures,parent,false);
        return new PlayerFeaturesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerFeaturesHolder holder, int position) {
        PlayerFeaturesForProfile pf = pfList.get(position);
        holder.pfGameName.setText(pf.getgUserName());
        Picasso.with(context)
                .load(ApiUtils.BASE_URL +"images/"+pf.getGameImage())
                .transform(new CircleTransform())
                .fit()
                .centerInside()
                .into(holder.pfGameImage);
        holder.pfGameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerFeaturesForProfileDialog dialog = new PlayerFeaturesForProfileDialog(context);
                dialog.showDialog(activity,pf);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pfList.size();
    }

    public class PlayerFeaturesHolder extends RecyclerView.ViewHolder {

        ImageView pfGameImage;
        TextView pfGameName;

        public PlayerFeaturesHolder(@NonNull View itemView) {
            super(itemView);
            pfGameImage = itemView.findViewById(R.id.pfGameImage);
            pfGameName = itemView.findViewById(R.id.pfGameName);
        }
    }


}
