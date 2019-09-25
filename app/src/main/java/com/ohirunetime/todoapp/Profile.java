package com.ohirunetime.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import com.ohirunetime.todoapp.api.ApiClient;
import com.ohirunetime.todoapp.api.ApiInterface;
import com.ohirunetime.todoapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ohirunetime.todoapp.R;

public class Profile extends AppCompatActivity {

    ApiInterface apiInterface;

    Button saveButton;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        saveButton = findViewById(R.id.save_name);
        et_name=findViewById(R.id.name);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = et_name.getText().toString().trim();

                if (name.isEmpty()) {
                    et_name.setError("入力してください('◇')ゞ");
                } else {


                    SharedPreferences UuidStore = getSharedPreferences("UuidStore",MODE_PRIVATE);

                    String uuid = UuidStore.getString("uuid","");

                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<User> call = apiInterface.saveProfile(uuid,name);

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(Profile.this,"ようこそ"+name+"さん",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                    finish();

                }

            }
        });

    }
}
