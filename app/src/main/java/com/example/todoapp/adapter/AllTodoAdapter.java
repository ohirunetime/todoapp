package com.example.todoapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.model.Todo;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AllTodoAdapter extends RecyclerView.Adapter<AllTodoAdapter.AllTodoViewHolder> {

    private List<Todo> todoList;
    private Context context;



    public AllTodoAdapter (Context context ,List<Todo> todoList) {
        this.context=context;
        this.todoList=todoList;
    }

    class AllTodoViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        RelativeLayout relativeLayout;

        TextView txtdescription , txtcreated_at , txtname , txt_update_at;

        AllTodoViewHolder(View itemView) {
            super(itemView);
            mView=itemView;

            txtdescription=mView.findViewById(R.id.txt_description);
            txtcreated_at=mView.findViewById(R.id.txt_created_at);
            txtname=mView.findViewById(R.id.txt_name);
            txt_update_at=mView.findViewById(R.id.txt_update_at);

            relativeLayout=mView.findViewById(R.id.RelativeLayout);

        }
    }


    @Override
    public AllTodoViewHolder onCreateViewHolder (ViewGroup parent , int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.alltodo_row,parent,false);
        return new AllTodoViewHolder(view);
    }
    @Override
    public void onBindViewHolder (AllTodoViewHolder holder , int position) {
        holder.txtdescription.setText(todoList.get(position).getDescription());
        holder.txtcreated_at.setText(todoList.get(position).getCreated_at());
        holder.txtname.setText(todoList.get(position).getName());
        holder.txt_update_at.setText(todoList.get(position).getUpdate_at());


        holder.relativeLayout.setBackgroundColor(Color.parseColor(todoList.get(position).getColor()));






    }



    @Override
    public int getItemCount() {
        return todoList.size();
    }





}
