package com.example.gamingsmlogin.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.Message;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//single message kısmı
public class ChatRVAdapter extends RecyclerView.Adapter<ChatRVAdapter.ChatAdapterHolder> {
    List<Message> list;
    Context context;
    String logUserID="1";//sqlite
    IServices services = ApiUtils.getIServices();
    public ChatRVAdapter(List<Message> list, Context context) {
        this.list = list;
        this.context = context;
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        logUserID=String.valueOf( dao.getSQLUser(db).get(0).getUserID()) ;
    }

    @NonNull
    @Override
    public ChatAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_single_message_chat,parent,false);
        return new ChatAdapterHolder(v);
    }
    private void setDesignPhoto(TextView TVGone ,ImageView ImgVisible,String Id){
        TVGone.setVisibility(View.GONE);
        ImgVisible.setVisibility(View.VISIBLE);
        setImg(ImgVisible,Id);
    }
    private void setDesignVideo(TextView TVGone ,VideoView VidVisible,String Id){
        TVGone.setVisibility(View.GONE);
        VidVisible.setVisibility(View.VISIBLE);
        setVideo(VidVisible,Id);
    }

    private void setProfImage(ImageView iv,String url){

        if(url.equals("") || url.equals(" ")) {
            Picasso.with(context).load(R.drawable.def_profile).transform(new CircleTransform()).into(iv);
        }else{
            Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+ url).transform(new CircleTransform()).into(iv);

        }
    }
    ///düzeltilicekkkkkkkkkk profil resmi kısmı
    @Override
    public void onBindViewHolder(@NonNull ChatAdapterHolder holder, int i) {
        Message m = list.get(i);
         if(m.getSenderID().equals(logUserID)){
            holder.receiver.setVisibility(View.GONE);
             holder.senderImage.setVisibility(View.VISIBLE);
            setProfImage(holder.senderImage,m.getSenderImage());
            if(m.getType().equals("2")){//resim
                setDesignPhoto(holder.senderText,holder.sendImageSender,m.getMessagesId());
            }else if(m.getType().equals("3")){//video
                setDesignVideo(holder.senderText,holder.vVSender,m.getMessagesId());
            }
            else{
                setMessage( holder.senderText,m);
                 }
        }else{
            holder.sender.setVisibility(View.GONE);
            setProfImage(holder.receiverImage,m.getReciverImage());
            if(m.getType().equals("2")){
                setDesignPhoto(holder.receiverText,holder.sendImageReceiver,m.getMessagesId());
            }else if(m.getType().equals("3")){
                setDesignVideo(holder.receiverText,holder.vVReceiver,m.getMessagesId());
            }else {
                holder.sender.setVisibility(View.GONE);
                setMessage(holder.receiverText, m);
              }
        }


    }


    private void setVideo(VideoView videoView,String fileId) {
        services.getUrl("getUrl",fileId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                ControlReturn s = response.body();
                //Use a media controller so that you can scroll the video contents
                //and also to pause, start the video.

                videoView.setVideoURI(Uri.parse(ApiUtils.BASE_URL+"images/"+s.getControl()));
                videoView.seekTo( 1 );
                videoView.setOnPreparedListener(mp -> {
                    // TODO Auto-generated method stub
                    videoView.setOnClickListener(v -> {
                        if(!mp.isPlaying()){
                            mp.start();
                        }
                        mp.setLooping(true);

                    });
                });
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("baglanti hatasi", "Vieo yükleme",t );
            }

        });
    }
    private void setImg(ImageView imageView,String fileId){
        services.getUrl("getUrl",fileId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                ControlReturn s =response.body();
                Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+s.getControl()).into(imageView);
            }
            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("baglanti hatasi", "Resim yükleme",t );
            }
        });
    }
    private void setMessage(TextView textView, Message message){
        //getuserName;
        textView.setText(message.getValue());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public  class ChatAdapterHolder extends RecyclerView.ViewHolder {
        CardView sender;
        CardView receiver;
        TextView senderText;
        TextView receiverText;
        ImageView senderImage;
        ImageView sendImageSender;
        ImageView sendImageReceiver;
        ImageView receiverImage;
        VideoView vVSender;
        VideoView vVReceiver;
        LinearLayout linSender;
        LinearLayout linReceiver;
        public ChatAdapterHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.Senderer);
            receiver = itemView.findViewById(R.id.Reciverer);
            sendImageSender = itemView.findViewById(R.id.imageViewSender);
            linSender = itemView.findViewById(R.id.LinSender);
            linReceiver = itemView.findViewById(R.id.LinReciver);
            sendImageReceiver = itemView.findViewById(R.id.imageViewReciver);
            senderText = itemView.findViewById(R.id.SerderText);
            vVSender = itemView.findViewById(R.id.videoViewSender);
            vVReceiver = itemView.findViewById(R.id.videoViewReciver);
            receiverText = itemView.findViewById(R.id.ReciverText);
            senderImage = itemView.findViewById(R.id.SerderImage);
            receiverImage = itemView.findViewById(R.id.ReciverImage);
        }
    }


}
