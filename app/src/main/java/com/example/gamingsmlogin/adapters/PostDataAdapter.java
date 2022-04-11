package com.example.gamingsmlogin.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.File;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

//post paylaşımı video ve resim kısmı için
public class PostDataAdapter extends PagerAdapter {

    List<File>  images;
    LayoutInflater inflater;
    Context context;

    public PostDataAdapter(Context context, List<File> images){
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object){
        container.removeView((View)object);
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position){
        View v  = inflater.inflate(R.layout.card_post_data, view, false);
        File f = images.get(position);
        VideoView videoView = v.findViewById(R.id.videoView);
        ImageView imageView =  v.findViewById(R.id.image);

        if(f.getType().equals("1")){
            videoView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            videoView.setVideoURI(Uri.parse(ApiUtils.BASE_URL+"images/"+f.getUrl()));
            videoView.seekTo( 1 );
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    videoView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!mp.isPlaying()){
                                mp.start();
                            }
                            mp.setLooping(true);

                        }
                    });
                }
            });
        }else{
            Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+f.getUrl()).into(imageView);
        }
        Log.e("TAG", "instantiateItem: "+ images.get(position).getUrl());
        view.addView(v, 0);
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, @NonNull Object object) {
        return view.equals(object);
    }
}