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

import com.example.gamingsmlogin.CommentPageActivity;
import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.ViewUserForSearchActivity;
import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.classes.Comment;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.management.DateManagment;
import com.squareup.picasso.Picasso;

import java.util.List;

//yorum çağırıcı
public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.BubbleAdapterHolder> {
    List<Comment> list;
    Context context;
    public CommentRVAdapter(List<Comment> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BubbleAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_global_chat,parent,false);
        return new BubbleAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BubbleAdapterHolder holder, int position) {
        Comment c = list.get(position);
        //getselecteruser image
        holder.recUserName.setText(String.format("%s %s", c.getName(), c.getLastName()));
        if(c.getProfilePhoto().equals("") ||c.getProfilePhoto().equals(" ")) {
            Picasso.with(context).load(R.drawable.def_profile).transform(new CircleTransform()).into(holder.recImage);
        }else{
            Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+ c.getProfilePhoto()).transform(new CircleTransform()).into(holder.recImage);
            Log.e("TAG", "onBindViewHolder: "+ApiUtils.BASE_URL+"images/"+ c.getProfilePhoto() );
        }
        holder.recImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewUserForSearchActivity.class).putExtra("userIDForProf",c.getUserID());
                context.startActivity(i);
            }
        });

        holder.recText.setText(c.getValue());
        DateManagment Dm= new DateManagment();
        holder.recDate.setText( Dm.whichShow(c.getDate()));
        int subcommentcount=Integer.parseInt(c.getSubCommentCount());
        //if(0<subcommentcount){
        //alt yorum vardır
        if(!c.getSubCommentCount().equals("0")){
            holder.subCommentCounter.setVisibility(View.VISIBLE);
            holder.subCommentCounter.setText(c.getSubCommentCount()+" Yanıtı Gör");
        }
        if(!c.getIsSubComment().equals("1")){
            holder.recCard.setOnClickListener(v -> {
                Intent i1 = new Intent(context, CommentPageActivity.class);
                i1.putExtra("connectId",c.getCommentsId());
                i1.putExtra("sub",1);//subcomment Active
                Log.e("TAG", "sub: "+ c.getCommentsId());
                context.startActivity(i1);
            });
        }
        //}

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class BubbleAdapterHolder extends RecyclerView.ViewHolder {
        CardView recCard;
        TextView recText,subCommentCounter,recDate,recUserName;
        ImageView recImage;
        public BubbleAdapterHolder(@NonNull View itemView) {
            super(itemView);
            recUserName = itemView.findViewById(R.id.recUserName);
            recText = itemView.findViewById(R.id.recText);
            subCommentCounter = itemView.findViewById(R.id.subCommentCounter);
            recDate = itemView.findViewById(R.id.recDate);
            recCard = itemView.findViewById(R.id.cardView);
            recImage = itemView.findViewById(R.id.reciverImage);
        }
    }

}
