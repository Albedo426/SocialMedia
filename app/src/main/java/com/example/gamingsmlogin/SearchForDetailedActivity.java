package com.example.gamingsmlogin;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.adapters.DetailedSearchRVAdapter;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.management.SearchManagement;
import com.example.gamingsmlogin.management.UsersManagement;

import java.util.ArrayList;
import java.util.List;

//kullanıcıları detaylı arama
public class SearchForDetailedActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<User> list;
    String whichMethod = "0";
    ImageView filterImage;
    TextView gamerOrUserTextView;
    boolean gamerOrUser=false;//kullanıcı adi ile arama otomatik
    UsersManagement um;
    DetailedSearchRVAdapter detailedSearchRVAdapter;
    SearchManagement sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_detailed);

        recyclerView = findViewById(R.id.recy);
        gamerOrUserTextView = findViewById(R.id.nameTyper);
        filterImage = findViewById(R.id.imageFilterView);

        final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        list= new ArrayList<>();
        um = new UsersManagement( recyclerView, list,this) ;
        sm = new SearchManagement(this,recyclerView);

        List<User> getFilteredUserList = (List<User>) getIntent().getSerializableExtra("getFilteredUserList");
        if(getFilteredUserList != null){
            detailedSearchRVAdapter = new DetailedSearchRVAdapter(getFilteredUserList,this);
            recyclerView.setAdapter(detailedSearchRVAdapter);
        }



        toolbar=findViewById(R.id.toolbarSearchPage);
        toolbar.setTitle("arama");
        setSupportActionBar(toolbar);

        gamerOrUserTextView.setOnClickListener(v -> {
            if(gamerOrUser){
                gamerOrUserTextView.setText("Kullanıcı adı ile arama");
                whichMethod = "0";
            }else{
                gamerOrUserTextView.setText("Oyuncu adı ile arama");
                whichMethod = "1";
            }
            gamerOrUser=!gamerOrUser;
        });

        filterImage.setOnClickListener(view -> startActivity(new Intent(SearchForDetailedActivity.this,GameFilterDialogActivity.class)));


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_user_for_message, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        //SÜRÜM FARKI İÇİN
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getUser(query+"");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getUser(newText+"");
        return false;
    }

    private void getUser(String s) {
        sm.detailedSearch(s,whichMethod);
    }
}