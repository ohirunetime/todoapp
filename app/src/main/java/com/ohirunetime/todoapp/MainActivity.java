package com.ohirunetime.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.AppLaunchChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.ohirunetime.todoapp.Fragment.Fragment1;
import com.ohirunetime.todoapp.Fragment.Fragment2;
import com.ohirunetime.todoapp.Fragment.Fragment3;
import com.ohirunetime.todoapp.api.ApiClient;
import com.ohirunetime.todoapp.api.ApiInterface;
import com.ohirunetime.todoapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ohirunetime.todoapp.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    Button button3;

    Fragment fragment;

    ApiInterface apiInterface;

    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3=findViewById(R.id.button3);
        button3.setOnClickListener(this);


        SharedPreferences UuidStore = getSharedPreferences("UuidStore",MODE_PRIVATE);

        if (AppLaunchChecker.hasStartedFromLauncher(this)) {
            Log.d("time","much");
            String uuid = UuidStore.getString("uuid","");
            Log.d("uuid=",uuid);

        } else {
            Log.d("初めて", "");

            String uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = UuidStore.edit();
            editor.putString("uuid", uuid);
            editor.apply();
            Log.d("uuid=", uuid);

            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

            Call<User> call = apiInterface.saveUser(uuid);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Intent intent = new Intent(MainActivity.this,Profile.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "welcome!!", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "サーバーエラー", Toast.LENGTH_LONG).show();

                }
            });
        }
        AppLaunchChecker.onActivityCreate(this);

        fragment = new Fragment1();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    private void displayFragment(int fragmentNumber) {
        Fragment fragment;

        switch (fragmentNumber) {
            case 1:
                fragment = new Fragment1();
                System.out.println("kitatttttttttttttt");
                break;

            case 2:
                fragment=new Fragment2();
                break;

            case 3:
                fragment=new Fragment3();
                break;

            default:
                return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        if (v.equals(button1)) {
            displayFragment(1);
        }
        else if (v.equals(button2)) {
            displayFragment(2);
        }
        else if (v.equals(button3)) {
            displayFragment(3);
        }
    }
}
