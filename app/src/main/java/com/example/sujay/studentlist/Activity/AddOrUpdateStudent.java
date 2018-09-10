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

public class AddOrUpdateStudent extends AppCompatActivity {

    EditText student_name,student_address,student_mboile;
    Button addStudentBtn,updateStudentBtn;
    Student student;
    int studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);



        student_name = findViewById(R.id.nameTxt);
        student_address = findViewById(R.id.addressTxt);
        student_mboile = findViewById(R.id.mobile_no_txt);

        addStudentBtn = findViewById(R.id.addStudentBtn);
        updateStudentBtn = findViewById(R.id.updateStudentBtn);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("studentData"))
            student = (Student) bundle.getSerializable("studentData");

        else{
            student_name.setText("");
            student_address.setText("");
            student_mboile.setText("");

            updateStudentBtn.setVisibility(View.GONE);
            addStudentBtn.setVisibility(View.VISIBLE);
        }

        if(student!=null){

            student_name.setText(student.getName());
            student_address.setText(student.getAddress());
            student_mboile.setText(""+student.getMobile_no());
            studentId = student.getId();
            updateStudentBtn.setVisibility(View.VISIBLE);
            addStudentBtn.setVisibility(View.GONE);
        }


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
                db.close();
                Toast.makeText(getApplicationContext(),"Student added Successfully ! ",Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

        updateStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"StudentDatabase")
                        .allowMainThreadQueries()
                        .build();
                Student updateStudent = new Student();
                updateStudent.setId(studentId);
                updateStudent.setName(student_name.getText().toString());
                updateStudent.setAddress(student_address.getText().toString());
                updateStudent.setMobile_no(Integer.parseInt(student_mboile.getText().toString()));

                db.studentDao().updateRecords(updateStudent);
                db.close();
                Toast.makeText(getApplicationContext(),"Student Updated Successfully ! ",Toast.LENGTH_SHORT).show();

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
