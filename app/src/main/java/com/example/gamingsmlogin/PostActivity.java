package com.example.gamingsmlogin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gamingsmlogin.fragments.mainFragments.PostShareFragment;

//Kulolanıcı arama //düzenlendi // bu kısım tamamen örnek fragment sistemi yaptığımızda ana kaydırma olucak yani ;
// main sayfa /post paylaş/ ayarlar gibi yana kaydırarark bu sayfalara erişecek  yani bu tamamen test  için  ilerde çıkarılarak veya değiştirilecek
public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        PostShareFragment psFragment= new PostShareFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transcation= fm.beginTransaction();
        transcation.add(R.id.post,psFragment,"first");
        transcation.commit();
    }
}