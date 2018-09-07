package com.example.sujay.studentlist.Activity;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.sujay.studentlist.Utils.Student;
import com.example.sujay.studentlist.Utils.StudentDao;


@Database(entities = {Student.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
}
