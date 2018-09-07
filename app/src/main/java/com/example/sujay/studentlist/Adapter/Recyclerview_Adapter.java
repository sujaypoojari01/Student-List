package com.example.sujay.studentlist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sujay.studentlist.R;
import com.example.sujay.studentlist.Utils.Student;

import java.util.List;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.Viewholder> {
    List<Student> studentList;
    Context context;

    public Recyclerview_Adapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.name.setText(studentList.get(position).getName());
        holder.address.setText(studentList.get(position).getAddress());
        holder.mobile.setText(""+studentList.get(position).getMobile_no());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name,address,mobile;
        CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            mobile = itemView.findViewById(R.id.mobile_no);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
