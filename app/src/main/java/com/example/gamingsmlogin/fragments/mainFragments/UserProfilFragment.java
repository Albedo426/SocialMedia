package com.example.gamingsmlogin.fragments.mainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.SettingsMain;
import com.example.gamingsmlogin.classes.PlayerFeaturesForProfile;
import com.example.gamingsmlogin.classes.Post;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.management.PostManagement;
import com.example.gamingsmlogin.management.UserProfileManagement;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.util.ArrayList;
import java.util.List;


//hazır değil
public class UserProfilFragment extends Fragment {

    ImageView prfImage,friendImage;
    boolean notsqluser=true;
    TextView userName;
    TextView settingTextView;
    String id;
    int who;
    IServices services = ApiUtils.getIServices();
    RecyclerView recyclerView;
    RecyclerView playerFeaturesRV;
    PostManagement pManagment;
    int limit=10;
    List<Post> list;
    List<PlayerFeaturesForProfile> pfList;
    RecyclerRefreshLayout recyclerRefreshLayout;
    public UserProfilFragment(int who,String id) {
        this.who = who; // 0 kendi profili 1 başkasının profili.
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_user_profil, container, false);

        prfImage = v.findViewById(R.id.imageViewProfile);
        settingTextView = v.findViewById(R.id.settingTextView);
        recyclerRefreshLayout = v.findViewById(R.id.RecyclerRefreshlayout);

        friendImage = v.findViewById(R.id.friendImage);

        recyclerView = v.findViewById(R.id.recyclerView);
        playerFeaturesRV = v.findViewById(R.id.playerFeaturesRV);

        userName = v.findViewById(R.id.textViewNameSurname);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        playerFeaturesRV.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        playerFeaturesRV.setHasFixedSize(true);

        list = new ArrayList<>();

        UserProfileManagement upm = new UserProfileManagement(getContext());

        pManagment= new PostManagement(recyclerView,list,getContext());
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(getContext());

        if(getArguments()!=null){
            id = getArguments().getString("Id");
            notsqluser=false;
        }

        recyclerRefreshLayout.setOnRefreshListener(() -> {
            pManagment.getPostForId(limit,id);
            recyclerRefreshLayout.setRefreshing(false);
        });
        if((who == 1) &&(!id.equals(dao.getSQLUser(db).get(0).getUserID().toString()))){
            friendImage.setVisibility(View.VISIBLE);
        }else{
            friendImage.setVisibility(View.INVISIBLE);
            settingTextView.setVisibility(View.VISIBLE);
            settingTextView.setOnClickListener(v1 -> {
                Intent i=new Intent(getContext(), SettingsMain.class);
                startActivity(i);
            });
        }


        upm.isFriend(dao.getSQLUser(db).get(0).getUserID().toString(),id,friendImage);



        pManagment.getPostForId(limit,id);

        upm.getUser(id, userName, prfImage, getActivity());
        upm.getPlayerFeaturesWithUserID(id,playerFeaturesRV,getActivity());

        // güncellenecek managmente taşınıcak
        /*services.getUser("getUser", Id).enqueue(new Callback<UsersReturn>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                User u= response.body().getUsers().get(0);
                userName.setText(u.getName()+" \n"+u.getLastName());
                if(u.getProfilePhoto().equals("") ||u.getProfilePhoto().equals(" ")) {
                    Picasso.with(getContext()).load(R.drawable.def_profile).transform(new CircleTransform()).into(prfImage);
                }else{
                    Picasso.with(getContext()).load(ApiUtils.BASE_URL+"images/"+ u.getProfilePhoto()).transform(new CircleTransform()).into(prfImage);
                }
            }
            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("baglanti hatasi", "Kullanıcıyı getirirken",t );
                Toast.makeText(getActivity(), "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });*/


        return v;
    }
}