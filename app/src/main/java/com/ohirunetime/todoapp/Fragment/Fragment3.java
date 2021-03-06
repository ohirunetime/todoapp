package com.ohirunetime.todoapp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ohirunetime.todoapp.R;
import com.ohirunetime.todoapp.adapter.UserTodoAdapter;
import com.ohirunetime.todoapp.api.ApiClient;
import com.ohirunetime.todoapp.api.ApiInterface;
import com.ohirunetime.todoapp.model.Todo;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment3 extends Fragment {


    RecyclerView recyclerView;
    UserTodoAdapter userTodoAdapter;
    ProgressDialog progressDialog;







    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);
        progressDialog=new ProgressDialog(getActivity());


        progressDialog.setMessage("Loading ...");
        progressDialog.show();

        getTodo(view);

        return view;

    }


    public void getTodo(final View view) {
        final SharedPreferences UuidStore = getActivity().getSharedPreferences("UuidStore", Context.MODE_PRIVATE);
        String uuid = UuidStore.getString("uuid","");

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Todo>> call = apiInterface.getUserTodo(uuid);
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                progressDialog.dismiss();


                List<Todo> todouserList = response.body();

                recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerUsertodo);
                userTodoAdapter = new UserTodoAdapter(getActivity(),todouserList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(userTodoAdapter);

            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"通信エラー",Toast.LENGTH_LONG).show();


            }
        });
    }



}
