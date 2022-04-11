package com.example.gamingsmlogin.management;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.adapters.PlayerFeaturesForProfileRVAdapter;
import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.PlayerFeaturesForProfile;
import com.example.gamingsmlogin.classes.PlayerFeaturesForProfileReturn;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.classes.UsersReturn;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileManagement extends BaseManagement{


    public UserProfileManagement(Context context) {
        super(context);
    }

    IServices services = ApiUtils.getIServices();

    public void getUser(String id, TextView userName, ImageView prfImage, Activity activity){
        services.getUser("getUser", id).enqueue(new Callback<UsersReturn>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                User u= response.body().getUsers().get(0);
                userName.setText(u.getName()+" "+u.getLastName());
                if(u.getProfilePhoto().equals("") ||u.getProfilePhoto().equals(" ")) {
                    Picasso.with(context).load(R.drawable.def_profile).transform(new CircleTransform()).into(prfImage);
                }else{
                    Picasso.with(context).load(ApiUtils.BASE_URL+"images/"+ u.getProfilePhoto()).transform(new CircleTransform()).into(prfImage);
                }
            }
            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("UserProfileManagement", "connection error getUser()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPlayerFeaturesWithUserID(String id, RecyclerView recyclerView,Activity activity){
        services.getPlayerFeaturesWithUserID("getPlayerFeaturesWithUserID",id).enqueue(new Callback<PlayerFeaturesForProfileReturn>() {
            @Override
            public void onResponse(Call<PlayerFeaturesForProfileReturn> call, Response<PlayerFeaturesForProfileReturn> response) {
                List<PlayerFeaturesForProfile> pfList = response.body().getPlayerFeaturesForProfile();
                PlayerFeaturesForProfileRVAdapter pfAdapter = new PlayerFeaturesForProfileRVAdapter(activity,context,pfList);
                recyclerView.setAdapter(pfAdapter);
            }

            @Override
            public void onFailure(Call<PlayerFeaturesForProfileReturn> call, Throwable t) {
                Log.e("UserProfileManagement", "connection error getPlayerFeaturesWithUserID()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void pendingFriendRequest(String senderId, String receiverId,ImageView imageView){
        services.pendingFriendRequest("pendingFriendRequest",senderId,receiverId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                isFriend(senderId,receiverId,imageView);
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("UserProfileManagement", "connection error pendingFriendRequest()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addFriend(String senderId, String receiverId,ImageView imageView){
        Log.e("TAG", receiverId+"addFriend: "+ senderId);
        services.pendingFriendRequest("addFriend",receiverId,senderId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                isFriend(senderId,receiverId,imageView);
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("UserProfileManagement", "connection error addFriend()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteFriend(String senderId, String receiverId,ImageView imageView){
        services.pendingFriendRequest("deleteFriend",senderId,receiverId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                isFriend(senderId,receiverId,imageView);
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("UserProfileManagement", "connection error deleteFriend()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void isFriend(String senderId, String receiverId, ImageView imageView){
        services.isFriend("isFriend",senderId,receiverId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                if(response.body().getControl().equals("1")){
                    imageView.setImageResource(R.drawable.ic_round_person_remove_alt_1_24);
                }else if(response.body().getControl().equals("0")){
                    imageView.setImageResource(R.drawable.ic_baseline_pending_24);
                }else{
                    imageView.setImageResource(R.drawable.ic_round_person_add_alt_24);
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View view) {
                        if(imageView.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.ic_round_person_add_alt_24).getConstantState()){
                            pendingFriendRequest(senderId,receiverId,imageView);
                        }else if(imageView.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.ic_baseline_pending_24).getConstantState()){
                            addFriend(senderId,receiverId,imageView);
                        }else if(imageView.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.ic_round_person_remove_alt_1_24).getConstantState()){
                            deleteFriend(senderId,receiverId,imageView);
                        }
                    }
                });
                Log.e("isFriend",  response.body().getControl());
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("isFriend",  t.getMessage());
            }
        });
    }*/
    private void createAlertBuilder(ImageView v,String senderId,String receiverId){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Arkadaş İsteği")
                .setMessage("Onaylamak İçin")
                .setPositiveButton("Onayla", (dialog, which) -> {
                    addFriend( receiverId,  senderId,v);
                   // v.setImageResource(R.drawable.ic_round_person_remove_alt_1_24);
                })
                .setNegativeButton("Reddet", (dialog, which) -> {
                    deleteFriend(senderId,  receiverId,v) ;
                });
        builder.show();
    }
    UserDAO dao = new UserDAO();
    DbHelper dbHelper = new DbHelper(context);

    public void isFriend(String senderId, String receiverId, ImageView imageView){
        services.isFriend("isFriend",senderId,receiverId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                if(response.body().getControl().equals("0")){
                    imageView.setImageResource(R.drawable.ic_round_person_remove_alt_1_24);
                    imageView.setOnClickListener(view -> deleteFriend(senderId,receiverId,imageView));
                }else if(response.body().getControl().equals("-1")){
                    imageView.setImageResource(R.drawable.ic_round_person_add_alt_24);
                    imageView.setOnClickListener(view -> pendingFriendRequest(senderId,receiverId,imageView));
                }
                else{
                    imageView.setImageResource(R.drawable.ic_baseline_pending_24);
                    Log.e("TAG", receiverId+"onResponse:"+dao.getSQLUser(dbHelper).get(0).getUserID() );
                    if(Integer.valueOf(response.body().getControl()).equals(dao.getSQLUser(dbHelper).get(0).getUserID())) {
                        imageView.setOnClickListener(v -> createAlertBuilder(imageView, receiverId, response.body().getControl()));
                    }
                }

            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("UserProfileManagement", "connection error isFriend()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
