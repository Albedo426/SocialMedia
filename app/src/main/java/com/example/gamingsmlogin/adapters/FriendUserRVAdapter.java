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

import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.SingleMessageChatActivity;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FriendUserRVAdapter extends RecyclerView.Adapter<FriendUserRVAdapter.FriendUserAdapterHolder>{
    List<User> list;
    Context context;

    public FriendUserRVAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendUserAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_global_chat,parent,false);
        return new FriendUserAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendUserAdapterHolder holder, int position) {
        User u = list.get(position);
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
                Intent i = new Intent(context, SingleMessageChatActivity.class);
                i.putExtra("ifcrative",0);//yeni mesaj oluşturacağına dair bilgi
                i.putExtra("reciver",u.getUserID().toString() );
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FriendUserAdapterHolder extends RecyclerView.ViewHolder {
        CardView recCard;
        TextView recUserName;
        ImageView recImage;
        public FriendUserAdapterHolder(@NonNull View itemView) {
            super(itemView);
            recUserName = itemView.findViewById(R.id.recUserName);
            recCard = itemView.findViewById(R.id.cardView);
            recImage = itemView.findViewById(R.id.reciverImage);
        }
    }

}
