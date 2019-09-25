package com.ohirunetime.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ohirunetime.todoapp.R;
import com.ohirunetime.todoapp.api.ApiClient;
import com.ohirunetime.todoapp.api.ApiInterface;
import com.ohirunetime.todoapp.model.Todo;

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


        public final View mView;
        TextView txtdescription , txtcreated_at , txtupdate_at, txtname , buttonViewOption;


        UserTodoViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            txtdescription=mView.findViewById(R.id.txt_description);
            txtcreated_at=mView.findViewById(R.id.txt_created_at);
            txtupdate_at=mView.findViewById(R.id.txt_update_at);

            txtname=mView.findViewById(R.id.txt_name);




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
    public void onBindViewHolder(final UserTodoViewHolder holder , final int position ) {
        holder.txtdescription.setText(todouserList.get(position).getDescription());
        holder.txtcreated_at.setText(todouserList.get(position).getCreated_at());
        holder.txtname.setText(todouserList.get(position).getName());
        holder.txtupdate_at.setText(todouserList.get(position).getUpdate_at());





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
                                update(todoid);

                                todouserList.remove(position);
                                notifyItemRemoved(position);
                                break;


                            case R.id.menu4:
                                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                                Call<Todo> call = apiInterface.delete(todoid);
                                call.enqueue(new Callback<Todo>() {
                                    @Override
                                    public void onResponse(Call<Todo> call, Response<Todo> response) {
                                        todouserList.remove(position);
                                        notifyItemRemoved(position);
                                        Toast.makeText(context,"削除しました",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Todo> call, Throwable t) {
                                        Toast.makeText(context,"通信エラーが発生しております",Toast.LENGTH_SHORT).show();

                                    }
                                });
                                break;
                        }
                        return false;
                    }
                });

                popup.show();

            }
        });

    }

    public void update(final int todoid){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Todo> call = apiInterface.updateTodo(todoid);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Toast.makeText(context,"お疲れ様です!!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Toast.makeText(context,"通信エラーが発生しております",Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public int getItemCount() {
        return todouserList.size();
    }










}


