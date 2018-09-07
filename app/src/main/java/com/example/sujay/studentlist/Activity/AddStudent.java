package com.example.sujay.studentlist.Activity;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sujay.studentlist.R;
import com.example.sujay.studentlist.Utils.Student;

public class AddStudent extends AppCompatActivity {

    EditText student_name,student_address,student_mboile;
    Button addStudentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        student_name = findViewById(R.id.nameTxt);
        student_address = findViewById(R.id.addressTxt);
        student_mboile = findViewById(R.id.mobile_no_txt);

        addStudentBtn = findViewById(R.id.addStudentBtn);

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"StudentDatabase")
                        .allowMainThreadQueries()
                        .build();
                db.studentDao().insertRecords(new Student
                           (student_name.getText().toString(),
                            student_address.getText().toString(),
                            Integer.parseInt(student_mboile.getText().toString())));
                Toast.makeText(getApplicationContext(),"Student added Successfully ! ",Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
