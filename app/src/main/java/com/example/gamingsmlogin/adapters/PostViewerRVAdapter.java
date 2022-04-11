package com.example.gamingsmlogin.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.gamingsmlogin.CommentPageActivity;
import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.File;
import com.example.gamingsmlogin.classes.FilesReturn;
import com.example.gamingsmlogin.classes.Post;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.fragments.mainFragments.UserProfilFragment;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.management.DateManagment;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//main postları getirir
public class PostViewerRVAdapter extends RecyclerView.Adapter<PostViewerRVAdapter.PostViewerAdapterHolder> {
    List<Post> list;
    Context context;
    IServices services = ApiUtils.getIServices();

    public PostViewerRVAdapter(List<Post> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewerAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_post, parent, false);
        return new PostViewerAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewerAdapterHolder holder, int position) {
        Post p = list.get(position);

        if(p.getProfilePhoto().equals("") ||p.getProfilePhoto().equals(" ")) {
            Picasso.with(context).load(R.drawable.def_profile).transform(new CircleTransform()).into(holder.userImage);
        }else{
            Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+ p.getProfilePhoto()).transform(new CircleTransform()).into(holder.userImage);
        }
        String s = p.getName() + " " + p.getLastName();
        holder.textViewName.setText(s);
        holder.constraintProf.setOnClickListener(view -> loadFragment(new UserProfilFragment(1,p.getUserId())));
        DateManagment Dm= new DateManagment();
        holder.textViewDate.setText( Dm.whichShow(p.getCreateDate()));
        holder.textViewPostVal.setText(p.getValue());
        holder.textViewLikeCount.setText(p.getLikeCount());
        holder.textViewCommentCount.setText(p.getCommentCount());
        int didLike = Integer.parseInt(p.getDidIslike());
        if(didLike>=1){
            holder.likeText.setTextColor(context.getResources().getColor(R.color.secondary));
        }
        holder.likeButton.setOnClickListener(v -> likeControl(  holder.textViewLikeCount ,  holder.likeText ,p));
        if(p.getDataIsNull().equals("1")){
            holder.daraRelative.setVisibility(View.GONE);
        }else{
            init(p.getPostId(),holder);
        }
        holder.postCommentLinear.setOnClickListener(v -> {
            Intent i = new Intent(context, CommentPageActivity.class);
            i.putExtra("connectId",p.getPostId());//get sqllite;
            context.startActivity(i);
        });


    }

    private void init(String Id,PostViewerAdapterHolder holder) {
        services.postDataGet("getPostData",Id).enqueue(new Callback<FilesReturn>() {
            @Override
            public void onResponse(Call<FilesReturn> call, Response<FilesReturn> response) {
                List<File> serverResponse = response.body().getFiles();
                if(serverResponse.size()==1||serverResponse.size()==0){
                    holder.indicator.setVisibility(View.INVISIBLE);
                }
                holder.pager.setAdapter(new PostDataAdapter(context, serverResponse));
                holder.indicator.setViewPager(holder.pager);
            }

            @Override
            public void onFailure(Call<FilesReturn> call, Throwable t) {
                Log.e("TAG", "yeni tür hata " );
            }
        });
    }

    public void likeControl(TextView likeCountTextView,TextView liketext, Post post) {
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        User u=dao.getSQLUser(db).get(0);
        services.postlikeControlSet("likeControlSet",u.getUserID().toString(),post.getPostId()).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                ControlReturn s = response.body();
                String result = s.getControl();
                int likeCount = 0;
                if(result.equals("-1")){
                    liketext.setTextColor(context.getResources().getColor(R.color.white));
                    likeCount = Integer.parseInt(post.getLikeCount())-1;

                }else if (result.equals("1")) {
                    liketext.setTextColor(context.getResources().getColor(R.color.secondary));
                    likeCount = Integer.parseInt(post.getLikeCount())+1;
                }
                post.setLikeCount(String.valueOf(likeCount));

                likeCountTextView.setText(String.valueOf(likeCount));

            }
            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("baglanti hatasi", "Vieo yükleme",t );
            }

        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class PostViewerAdapterHolder extends RecyclerView.ViewHolder {
        CardView recCard;
        TextView textViewName;
        ConstraintLayout constraintProf;
        TextView textViewDate;
        TextView textViewPostVal;
        TextView textViewLikeCount;
        TextView textViewCommentCount;
        RelativeLayout daraRelative;
        ViewPager pager;
        CircleIndicator indicator;
        TextView likeText;
        ImageView userImage;
        LinearLayout likeButton,postCommentLinear;
        public PostViewerAdapterHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.postsenderuser);
            textViewDate = itemView.findViewById(R.id.postSenderdate);
            constraintProf = itemView.findViewById(R.id.constraintProf);
            likeButton = itemView.findViewById(R.id.postlikelinear);
            daraRelative = itemView.findViewById(R.id.daraRelative);
            postCommentLinear = itemView.findViewById(R.id.postCommentLinear);
            pager = itemView.findViewById(R.id.pager);
            indicator = itemView.findViewById(R.id.indicator);
            likeText = itemView.findViewById(R.id.postLike);
            textViewPostVal = itemView.findViewById(R.id.postText);
            textViewLikeCount = itemView.findViewById(R.id.postLikeCount);
            textViewCommentCount = itemView.findViewById(R.id.postCommentCount);
            userImage = itemView.findViewById(R.id.postProfilimg);
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}