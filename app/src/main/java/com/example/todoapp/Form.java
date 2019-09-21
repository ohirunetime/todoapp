package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.api.ApiClient;
import com.example.todoapp.api.ApiInterface;
import com.example.todoapp.model.Todo;

public class Form extends AppCompatActivity {

    Button sendButton , editProfile;
    EditText et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editProfile=findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Form.this,Profile.class);
                startActivity(intent);
            }
        });






        et_description = findViewById(R.id.editText_description);
        sendButton = findViewById(R.id.sendButton);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences UuidStore = getSharedPreferences("UuidStore",MODE_PRIVATE);

                String description = et_description.getText().toString().trim();
                if (description.isEmpty()) {
                    et_description.setError("入力してください");
                }
                else {
                    String uuid = UuidStore.getString("uuid","");
                    System.out.println("uuid="+uuid);




                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<Todo> call = apiInterface.saveTodo(description,uuid);

                    call.enqueue(new Callback<Todo>() {
                        @Override
                        public void onResponse(Call<Todo> call, Response<Todo> response) {
                            et_description.setText("");
                            finish();



                        }

                        @Override
                        public void onFailure(Call<Todo> call, Throwable t) {

                        }
                    });

                }


            }

        });
    }
}
