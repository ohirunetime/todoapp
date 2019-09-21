package com.example.todoapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.api.ApiClient;
import com.example.todoapp.api.ApiInterface;
import com.example.todoapp.model.Counter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {
    RecyclerView recyclerView;

    TextView txt_counter;

    @Override

    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment2,container,false);

        addCount(view);

        callCount(view);

        return view;
    }


    public void callCount(final  View view) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Counter> call = apiInterface.getCounter();

        call.enqueue(new Callback<Counter>() {
            @Override
            public void onResponse(Call<Counter> call, Response<Counter> response) {

                Counter counter = response.body();
                txt_counter=view.findViewById(R.id.counter);
                txt_counter.setText(counter.getCount());


            }

            @Override
            public void onFailure(Call<Counter> call, Throwable t) {
                Toast.makeText(getActivity(),"通信エラー",Toast.LENGTH_LONG).show();

            }
        });
    }


    public void addCount(View view) {
        txt_counter =  view.findViewById(R.id.counter);


        txt_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<Counter> call = apiInterface.counter();
                call.enqueue(new Callback<Counter>() {
                    @Override
                    public void onResponse(Call<Counter> call, Response<Counter> response) {


                        int counter = Integer.parseInt(txt_counter.getText().toString());
                        counter +=1;
                        txt_counter.setText(String.valueOf(counter));
                        Toast.makeText(getActivity(),"お疲れ様!!\n24hにリセットされます",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<Counter> call, Throwable t) {

                        Toast.makeText(getActivity(),"通信エラー",Toast.LENGTH_LONG).show();

                    }
                });



            }
        });

    }
}
