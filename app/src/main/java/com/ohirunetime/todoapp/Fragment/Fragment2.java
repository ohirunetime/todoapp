package com.ohirunetime.todoapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ohirunetime.todoapp.Profile;
import com.ohirunetime.todoapp.R;
import com.ohirunetime.todoapp.api.ApiClient;
import com.ohirunetime.todoapp.api.ApiInterface;
import com.ohirunetime.todoapp.model.Todo;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {
    Button sendButton, editProfile;
    EditText et_description;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment2, container, false);

        editProfile = view.findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });


        et_description = view.findViewById(R.id.editText_description);
        sendButton = view.findViewById(R.id.sendButton);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences UuidStore = getActivity().getSharedPreferences("UuidStore", Context.MODE_PRIVATE);

                String description = et_description.getText().toString().trim();
                if (description.isEmpty()) {
                    et_description.setError("入力してください");
                } else {
                    String uuid = UuidStore.getString("uuid", "");
                    System.out.println("uuid=" + uuid);


                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                    Call<Todo> call = apiInterface.saveTodo(description, uuid);

                    call.enqueue(new Callback<Todo>() {
                        @Override
                        public void onResponse(Call<Todo> call, Response<Todo> response) {
                            et_description.setText("");
                            Toast.makeText(getActivity(),"送信されました\n今日も1日頑張りましょう!!",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Todo> call, Throwable t) {

                            Toast.makeText(getActivity(),"通信エラーが発生しております",Toast.LENGTH_LONG).show();

                        }
                    });

                }


            }

        });
        return view;
    }
}

