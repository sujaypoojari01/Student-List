package com.example.sujay.studentlist.Adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sujay.studentlist.Activity.AddOrUpdateStudent;
import com.example.sujay.studentlist.Activity.AppDatabase;
import com.example.sujay.studentlist.Activity.MainActivity;
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
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {

        holder.name.setText(studentList.get(position).getName());
        holder.address.setText(studentList.get(position).getAddress());
        holder.mobile.setText(""+studentList.get(position).getMobile_no());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student();
                Intent intent = new Intent(context, AddOrUpdateStudent.class);
                student.setId(studentList.get(position).getId());
                student.setName(studentList.get(position).getName());
                student.setAddress(studentList.get(position).getAddress());
                student.setMobile_no(studentList.get(position).getMobile_no());
                intent.putExtra("studentData",student);
                context.startActivity(intent);
            }
        });

        holder.deleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(context,AppDatabase.class,"StudentDatabase")
                        .allowMainThreadQueries()
                        .build();
                db.studentDao().deleteRecord(studentList.get(position));

                //Recyclerview_Adapter.this.notify();
                Toast.makeText(context,"Student added Successfully ! ",Toast.LENGTH_SHORT).show();

                db.close();
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name,address,mobile;
        CardView cardView,deleteCard;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            mobile = itemView.findViewById(R.id.mobile_no);
            cardView = itemView.findViewById(R.id.cardview);
            deleteCard = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
