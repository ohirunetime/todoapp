package com.example.todoapp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.adapter.AllTodoAdapter;
import com.example.todoapp.adapter.UserTodoAdapter;
import com.example.todoapp.api.ApiClient;
import com.example.todoapp.api.ApiInterface;
import com.example.todoapp.model.Todo;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment3 extends Fragment {

    Button sendButton;
    EditText et_description;
    RecyclerView recyclerView;
    UserTodoAdapter userTodoAdapter;






    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);

        send_function(view);

        getTodo(view);



        return view;

    }

    public  void send_function(View view) {
        et_description = view.findViewById(R.id.editText_description);
        sendButton = view.findViewById(R.id.sendButton);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences UuidStore = getActivity().getSharedPreferences("UuidStore", Context.MODE_PRIVATE);

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
                            Toast.makeText(getActivity(),"success",Toast.LENGTH_SHORT).show();
                            et_description.setText("");



                        }

                        @Override
                        public void onFailure(Call<Todo> call, Throwable t) {
                            Toast.makeText(getActivity(),"サーバーエラーが起きています\nただいま修復しております",Toast.LENGTH_LONG).show();

                        }
                    });

                }


            }

        });
    }
    public void getTodo(final View view) {
        final SharedPreferences UuidStore = getActivity().getSharedPreferences("UuidStore", Context.MODE_PRIVATE);
        String uuid = UuidStore.getString("uuid","");

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Todo>> call = apiInterface.getUserTodo(uuid);
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Toast.makeText(getActivity(),"メンテナンス中...",Toast.LENGTH_LONG).show();

                List<Todo> todouserList = response.body();

                recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerUsertodo);
                userTodoAdapter = new UserTodoAdapter(getActivity(),todouserList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(userTodoAdapter);

            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {

            }
        });
    }



}
