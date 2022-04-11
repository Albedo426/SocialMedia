package com.example.gamingsmlogin.fragments.mainFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.gamingsmlogin.LogSignInActivity;
import com.example.gamingsmlogin.MainMessageActivity;
import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.SearchForDetailedActivity;
import com.example.gamingsmlogin.classes.Post;
import com.example.gamingsmlogin.management.PostManagement;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//paylaşılan postların gösterildiği kısım //düzeltildi
public class HomePageFragment extends Fragment {

    RecyclerView recyclerView;
    PostManagement pManagement;
    RecyclerRefreshLayout recyclerRefreshLayout;
    Toolbar toolbar;
    int limit = 15;
    List<Post> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);

        recyclerView = v.findViewById(R.id.recy);
        toolbar = v.findViewById(R.id.toolbarHome);
        recyclerRefreshLayout=v.findViewById(R.id.RecyclerRefreshlayout);
        toolbar.setTitle("Ana Sayfa");
        setHasOptionsMenu(true);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        recyclerRefreshLayout.setOnRefreshListener(() -> {
            pManagement.getPost(limit);
            recyclerRefreshLayout.setRefreshing(false);
        });
        Context context = getContext();

        final LinearLayoutManager MANAGER = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(MANAGER);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();

        pManagement= new PostManagement(recyclerView,list,getContext());
        pManagement.getPost(limit);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_chat_button,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.chatBttn){
            startActivity(new Intent(getActivity(), MainMessageActivity.class));
            return true;
        }else if(item.getItemId() == R.id.logout_btn){
            DbHelper db =new DbHelper(getContext());
            new UserDAO().deleteEntry(db);
            startActivity(new Intent(getContext(), LogSignInActivity.class));
            return true;
        }else if(item.getItemId() == R.id.actionSearchUser){
            startActivity(new Intent(getActivity(), SearchForDetailedActivity.class));
        }
        return false;
    }
}