package com.example.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.model.Todo;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class UserTodoAdapter extends RecyclerView.Adapter<UserTodoAdapter.UserTodoViewHolder> {
    private List<Todo> todouserList;
    private Context context;

    public UserTodoAdapter(Context context , List<Todo> todouserList) {
        this.context=context;
        this.todouserList=todouserList;
    }


    class UserTodoViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView txtdescription , txtcreated_at , txtname , buttonViewOption;


        UserTodoViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            txtdescription=mView.findViewById(R.id.txt_description);
            txtcreated_at=mView.findViewById(R.id.txt_created_at);
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
    public void onBindViewHolder(final UserTodoViewHolder holder , int position ) {
        holder.txtdescription.setText(todouserList.get(position).getDescription());
        holder.txtcreated_at.setText(todouserList.get(position).getTo_char());
        holder.txtname.setText(todouserList.get(position).getName());

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
                                Toast.makeText(context,"item1",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu2:
                                Toast.makeText(context,"item2",Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        return todouserList.size();
    }









}


