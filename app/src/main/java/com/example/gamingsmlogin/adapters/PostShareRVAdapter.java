package com.example.gamingsmlogin.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.File;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//post paylaşımı için //düzeltildi //değişecek
public class PostShareRVAdapter extends RecyclerView.Adapter<PostShareRVAdapter.PostShareAdapterHolder> {
    List<File> list;
    Context context;
    boolean videoIsReady = false;
    IServices services = ApiUtils.getIServices();

    public PostShareRVAdapter(List<File> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PostShareAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_post_share_img, parent, false);
        return new PostShareAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostShareAdapterHolder holder, int i) {
        File f = list.get(i);
        if(f.getType().equals("0")){
            holder.videoView.setVisibility(View.GONE);
            setImg(f.getStorageUrl(),holder.postImg);
        }else{
            holder.postImg.setVisibility(View.GONE);
            crtMediaPlayer(f.getStorageUrl(),holder.videoView);
        }
        holder.postFileDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete img or visdeo sql or file
                if(f.getType().equals("1")){
                    if(videoIsReady){
                        removeAt(i);
                    }
                }else{
                    removeAt(i);
                }
            }
        });
    }
    public void removeAt(int position) {

        File file = list.get(position);
        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("siliniyor lütfen bekleyiniz...");
        progressDialog.show();
        services.removeFile("removeFile",file.getFileId(),file.getUrl()).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
                progressDialog.dismiss();
                Toast.makeText(context, "Silindi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("TAG", "do not removeAt: ",t );
                Toast.makeText(context, "Bir hata oluştu", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });



    }
    private void crtMediaPlayer(String url,VideoView videoView){
        //VV.setVideoURI(Uri.parse(ApiUtils.BASE_URL+"images/"+url));
        videoView.setVideoPath(url);
        videoView.seekTo( 2 );
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e("TAG", "tamamlandı" );
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoIsReady = true;
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

        //daha eklenmedi
    }
    private void setImg(String url,ImageView imageView){
        Picasso.with(context).load(url).into(imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PostShareAdapterHolder extends RecyclerView.ViewHolder {
        ImageView postImg;
        ImageView postFileDelete;
        VideoView videoView;

        public PostShareAdapterHolder(@NonNull View itemView) {
            super(itemView);
            postImg = itemView.findViewById(R.id.postimg);
            postFileDelete = itemView.findViewById(R.id.postFileDelete);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }
}