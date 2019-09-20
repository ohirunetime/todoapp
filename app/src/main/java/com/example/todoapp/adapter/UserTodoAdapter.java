package com.example.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        TextView txtdescription , txtcreated_at , txtname;


        UserTodoViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            txtdescription=mView.findViewById(R.id.txt_description);
            txtcreated_at=mView.findViewById(R.id.txt_created_at);
            txtname=mView.findViewById(R.id.txt_name);
        }
    }

    @Override
    public UserTodoViewHolder onCreateViewHolder (ViewGroup parent,int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.alltodo_row,parent,false);
        return new UserTodoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(UserTodoViewHolder holder ,int position ) {
        holder.txtdescription.setText(todouserList.get(position).getDescription());
        holder.txtcreated_at.setText(todouserList.get(position).getTo_char());
        holder.txtname.setText(todouserList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return todouserList.size();
    }







}


