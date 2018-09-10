package com.example.sujay.studentlist.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sujay.studentlist.R;
import com.example.sujay.studentlist.Utils.Student;

public class UpdateStudent extends AppCompatActivity {


    Student student ;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        Bundle bundle = getIntent().getExtras();

        if(bundle.containsKey("studentData"))
            student = (Student) bundle.getSerializable("studentData");
        /*if(studentInterface!=null)
            student = studentInterface.getStudent();
*/
        textView = findViewById(R.id.demoText);

        if(student!=null)
            textView.setText(student.getName());

    }
}
