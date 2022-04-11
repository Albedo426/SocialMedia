package com.example.gamingsmlogin.management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.MainActivity;
import com.example.gamingsmlogin.adapters.DetailedSearchRVAdapter;
import com.example.gamingsmlogin.adapters.FriendUserRVAdapter;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.classes.UsersReturn;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.stepstone.stepper.StepperLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersManagement extends BaseManagement {


    RecyclerView recyclerView;
    List<User> list;
    FriendUserRVAdapter friendUserAdapter;
    DetailedSearchRVAdapter detailedSearchRVAdapter;
    IServices services = ApiUtils.getIServices();
    String Id;
    public boolean check = true;
    //basemanagment eklendi diye super koydum çalışmaz ise dikkat
    public UsersManagement(Context context) {
        super(context);
    }

    public UsersManagement() {
        super();
    }

    //fyilmaz---
    public UsersManagement(RecyclerView recyclerView, List<User> list, Context context) {
        super(context);
        this.recyclerView = recyclerView;
        this.list = list;

        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        Id = String.valueOf(dao.getSQLUser(db).get(0).getUserID()); // DÜZELT
    }

    public void getFriendUser(String str){//getuser
        services.getFriendUser("getFriendUser", Id,str).enqueue(new Callback<UsersReturn>() {
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                fillData(response);
            }
            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("UserManagment", "connection error getFriendUser()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public boolean isValidPhone(CharSequence phone){
        return (!TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches());
    }
    public void getForLogin(String email, String password, View view, TextInputEditText textBox){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("TAG", "MyToken: "+refreshedToken );
        services.getForLogin("getForLogin",email,password,refreshedToken).enqueue(new Callback<UsersReturn>() {
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                List<User> userList = response.body().getUsers();
                if(userList!=null){
                    if (userList.get(0).getUserID() != 0){
                        insertUserTable(context,userList.get(0));
                        context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    }else{
                        Snackbar.make(view,"E-posta veya Şifre Hatalı Tekrar Denemek İster misin?",Snackbar.LENGTH_LONG)
                                .setAction("Tekrar Dene", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        textBox.setText("");
                                        textBox.requestFocus();
                                    }
                                }).show();
                    }
                }else{
                    Log.e("TAG", "userList null");
                }

            }
            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("UserManagment", "connection error getForLogin()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //kullanıcı oluşturmak için çalışan servis.

    public void onCreateUser(User user){
        services.onCreateUser("onCreateUser",user.getName(),user.getLastName(),user.getBirthDate(),user.getEmail(),user.getUserName(),user.getPassword(),user.getPhone())
                .enqueue(new Callback<ControlReturn>() {
                    @Override
                    public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                        Log.e("onCreateUser",response.body().getControl());
                        //context.startActivity(new Intent(context, GameSelectActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }

                    @Override
                    public void onFailure(Call<ControlReturn> call, Throwable t) {
                        Log.e("UserManagment", "connection error onCreateUser()",t );
                        Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void isDuplicate(String key, String value, TextInputEditText text, StepperLayout.OnNextClickedCallback callback){
        services.isDuplicate("isDuplicate",key,value).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                if (response.body().getControl().equals("1")) {
                    String result = "Bir sıkıntı meydana geldi.";
                    if (key.equals("userName")) {
                        result = "Bu kullanıcı adı daha önce alınmış.";
                    } else if (key.equals("email")){
                        result = "Bu e-posta adresi daha önce kullanılmış.";
                    }
                    Snackbar.make(text,result,Snackbar.LENGTH_LONG)
                            .setAction("Tekrar Dene", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    text.setText("");
                                    text.requestFocus();
                                }
                            })
                            .show();
                }else{
                    if (check){
                        callback.goToNextStep();
                    }
                    check = false;
                }
                Log.e("isDuplicate",response.body().getControl());
            }
            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("UserManagment", "connection error isDuplicate()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //enter tuşuna basıldığı zaman klavye kapanması için.

    public void keyListener(TextInputEditText text, Activity activity){
        text.setOnKeyListener((view, i, keyEvent) -> {
            if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if(activity.getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
            return false;
        });
    }
    //--- taşınacak burası
    /*public void detailedSearch(String value, String whichMethod){
        services.detailedSearch("detailedSearch",value,whichMethod).enqueue(new Callback<UsersReturn>() {
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                list= response.body().getUsers();
                detailedSearchRVAdapter = new DetailedSearchRVAdapter(list,context);
                recyclerView.setAdapter(detailedSearchRVAdapter);
            }

            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("aramaa ", t.getMessage());
            }
        });
    }

    public void getFilteredUser(String source1,String source2){
        services.getFilteredUser("getFilteredUser",source1,source2).enqueue(new Callback<UsersReturn>() {
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                Log.e("getFilteredUser", response.body().getUsers().get(0).getName());
            }

            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("getFilteredUser hata",t.getMessage());
            }
        });
    }*/

    //---
    public void insertUserTable(Context context,User u){
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        dao.insertUser(db,u);
        Log.e("UserManagment", "insertUserTable: insertUserTable " +dao.getSQLUser(db).get(0).getUserID());

    }

    private void fillData( Response<UsersReturn> response){
        list= response.body().getUsers();
        friendUserAdapter = new FriendUserRVAdapter(list,context);
        recyclerView.setAdapter(friendUserAdapter);
    }

    public void updateUser(User user){

        services.userUpdate("userUpdate",String.valueOf(user.getUserID()),
                user.getName(),user.getLastName(),user.getUserName(),user.getEmail(),
                user.getPassword(),user.getPhone(),user.getProfilePhoto()).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                if(response.body().getControl().equals("1")){
                    Toast.makeText(context, "güncelleme işlemi başarılı", Toast.LENGTH_SHORT).show();
                    UserDAO dao = new UserDAO();
                    DbHelper db = new DbHelper(context);
                    UserDAO.deleteEntry(db);
                    dao.insertUser(db,user);
                    context.startActivity(new Intent(context,MainActivity.class));
                }else{
                    Toast.makeText(context, "güncelleme işleminde bir hata meydana geldi", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("UserManagment", "connection error updateUser()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
