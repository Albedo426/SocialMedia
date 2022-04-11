package com.example.gamingsmlogin.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.Game;
import com.example.gamingsmlogin.classes.GameDialog;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameRVAdapter extends RecyclerView.Adapter<GameRVAdapter.GameAdapterHolder>{

    //Oyun seçim ekranındaki oyunları düzenliyor.

    Context context;
    Activity activity;
    List<Game> game;

    //contructor

    public GameRVAdapter(Context context, List<Game> game, Activity activity) {
        this.context = context;
        this.game = game;
        this.activity = activity;
    }

    //view nesnesi oluşturduk.

    @NonNull
    @Override
    public GameAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_game_view,parent,false);
        return new GameAdapterHolder(v);
    }

    //burada holder ile inner classda ne tanımladıysak onu kullanıyoruz.

    @Override
    public void onBindViewHolder(@NonNull GameAdapterHolder holder, int position) {
        Game g = game.get(position);
        Log.e("geldi",g.getGameImage());
        Picasso.with(context)
                .load(ApiUtils.BASE_URL +"images/"+g.getGameImage())
                .fit()
                .centerInside()
                .into(holder.gameImage);
        holder.gameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameDialog alert = new GameDialog(context);
                alert.showDialog(activity,g);
            }
        });
    }

    //kaç tane oyun olduğunu tutuyor.

    @Override
    public int getItemCount() {
        return game.size();
    }

    // hangi view nesneleri kullanılacaksa burada tanımlanıyor. Burası inner class.

    public class GameAdapterHolder extends RecyclerView.ViewHolder {
        ImageView gameImage;
        public GameAdapterHolder(@NonNull View itemView) {
            super(itemView);
            gameImage = itemView.findViewById(R.id.gameImage);
        }

    }
}
