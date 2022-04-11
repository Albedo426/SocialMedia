package com.example.gamingsmlogin.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.ViewUserForSearchActivity;
import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailedSearchRVAdapter extends RecyclerView.Adapter<DetailedSearchRVAdapter.DetailedSearchAdapterHolder>{

    List<User> users;
    Context context;

    public DetailedSearchRVAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailedSearchAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_global_chat,parent,false);
        return new DetailedSearchAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedSearchAdapterHolder holder, int position) {
        User u = users.get(position);
        holder.recUserName.setText(String.valueOf( u.getName()+" "+u.getLastName()));//mesajı oluşturan değil yani bu sayfayı çağıran user değil
        Log.e("TAG", "onBindViewHolder: "+u.getProfilePhoto() );
        if(u.getProfilePhoto().equals("") ||u.getProfilePhoto().equals(" ")) {
            Picasso.with(context).load(R.drawable.def_profile).transform(new CircleTransform()).into(holder.recImage);
        }else{
            Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+ u.getProfilePhoto()).transform(new CircleTransform()).into(holder.recImage);
        }
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewUserForSearchActivity.class);
                i.putExtra("userIDForProf",u.getUserID().toString());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class DetailedSearchAdapterHolder extends RecyclerView.ViewHolder{
        CardView recCard;
        TextView recUserName;
        ImageView recImage;
        public DetailedSearchAdapterHolder(@NonNull View itemView) {
            super(itemView);
            recUserName = itemView.findViewById(R.id.recUserName);
            recCard = itemView.findViewById(R.id.cardView);
            recImage = itemView.findViewById(R.id.reciverImage);
        }
    }
}
