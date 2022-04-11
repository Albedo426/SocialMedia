package com.example.gamingsmlogin.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.SingleMessageChatActivity;
import com.example.gamingsmlogin.classes.Bubble;
import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.management.DateManagment;
import com.squareup.picasso.Picasso;

import java.util.List;

//main yer ilk başta gelen mesdaj kutuları üstüne basıldığında single message ksımına gidiyor
public class BubbleRVAdapter extends RecyclerView.Adapter<BubbleRVAdapter.BubbleAdapterHolder> {
    List<Bubble> list;
    Context context;
    public BubbleRVAdapter(List<Bubble> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public BubbleAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_global_chat,parent,false);
        return new BubbleAdapterHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull BubbleAdapterHolder holder, int position) {
        Bubble b = list.get(position);
        //getselecteruser image
       // Log.e("TAG", "onBindViewHolder: "+b.getProfilePhoto() );
        holder.recUserName.setText(b.getName()+" "+b.getLastName());//Burda değişiklik olucak kullanıcının Idsinin yanında  adı da gelicem  class ve servis düzeltilicek
        if(b.getProfilePhoto().equals("") ||b.getProfilePhoto().equals(" ")) {
            Picasso.with(context).load(R.drawable.def_profile).transform(new CircleTransform()).into(holder.recImage);
        }else{
            Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+ b.getProfilePhoto()).transform(new CircleTransform()).into(holder.recImage);
        }
        holder.recText.setText(b.getValue());
        DateManagment Dm= new DateManagment();
        holder.recDate.setText( Dm.whichShow(b.getSendDate()));
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SingleMessageChatActivity.class);
                i.putExtra("reciver", b.getReceiverId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BubbleAdapterHolder extends RecyclerView.ViewHolder {
        CardView recCard;
        TextView recText;
        TextView recDate;
        TextView recUserName;
        ImageView recImage;
        public BubbleAdapterHolder(@NonNull View itemView) {
            super(itemView);
            recUserName=itemView.findViewById(R.id.recUserName);
            recText=itemView.findViewById(R.id.recText);
            recDate=itemView.findViewById(R.id.recDate);
            recCard=itemView.findViewById(R.id.cardView);
            recImage=itemView.findViewById(R.id.reciverImage);
        }
    }
}
