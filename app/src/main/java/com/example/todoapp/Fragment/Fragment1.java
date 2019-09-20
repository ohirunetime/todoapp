package com.example.todoapp.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
import com.example.todoapp.adapter.AllTodoAdapter;
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

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    private AllTodoAdapter allTodoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment1,container,false);
        progressDialog=new ProgressDialog(getActivity());


        progressDialog.setMessage("更新中...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Todo>> call = apiInterface.getAllTodo();

        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                progressDialog.dismiss();
                System.out.println("success");

                List<Todo> todoList = response.body();

                recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerView);
                allTodoAdapter = new AllTodoAdapter(getActivity(),todoList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(allTodoAdapter);

            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "通信エラー", Toast.LENGTH_LONG).show();
                System.out.println("error="+t);

            }
        });

        return view;
    }
}
