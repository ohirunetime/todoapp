package com.example.todoapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.api.ApiClient;
import com.example.todoapp.api.ApiInterface;
import com.example.todoapp.model.Todo;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTodoAdapter extends RecyclerView.Adapter<UserTodoAdapter.UserTodoViewHolder> {
    private List<Todo> todouserList;
    private Context context;

    public UserTodoAdapter(Context context , List<Todo> todouserList) {
        this.context=context;
        this.todouserList=todouserList;
    }


    class UserTodoViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;

        public final View mView;
        TextView txtdescription , txtcreated_at , txtupdate_at, txtname , buttonViewOption;


        UserTodoViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            txtdescription=mView.findViewById(R.id.txt_description);
            txtcreated_at=mView.findViewById(R.id.txt_created_at);
            txtupdate_at=mView.findViewById(R.id.txt_update_at);

            txtname=mView.findViewById(R.id.txt_name);

            relativeLayout=mView.findViewById(R.id.RelativeLayout);



            buttonViewOption=mView.findViewById(R.id.textViewOptions);
        }
    }

    @Override
    public UserTodoViewHolder onCreateViewHolder (ViewGroup parent,int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_todo_row ,parent,false);
        return new UserTodoViewHolder(view);






    }
    @Override
    public void onBindViewHolder(final UserTodoViewHolder holder , int position ) {
        holder.txtdescription.setText(todouserList.get(position).getDescription());
        holder.txtcreated_at.setText(todouserList.get(position).getCreated_at());
        holder.txtname.setText(todouserList.get(position).getName());
        holder.txtupdate_at.setText(todouserList.get(position).getUpdate_at());

        holder.relativeLayout.setBackgroundColor(Color.parseColor(todouserList.get(position).getColor()));




        final int todoid = todouserList.get(position).getId();
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(context,holder.buttonViewOption);
                popup.inflate(R.menu.options_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                String color = "#FF9DAE";
                                update(todoid,color);
                                break;
                            case R.id.menu2:
                                String color1 = "#FF00E5FF";
                                update(todoid,color1);
                                break;
                            case R.id.menu3:
                                Toast.makeText(context,"item3",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

                popup.show();

            }
        });

    }

    public void update(final int todoid, final String color ){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Todo> call = apiInterface.updateTodo(todoid,color);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Toast.makeText(context,"紅蓮な狼煙があがりました",Toast.LENGTH_SHORT).show();
                System.out.println("todoid"+todoid+"color="+color);
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return todouserList.size();
    }









}


