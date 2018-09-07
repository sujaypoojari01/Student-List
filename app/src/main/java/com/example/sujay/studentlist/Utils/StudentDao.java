package com.example.sujay.studentlist.Utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("select * from students")
    List<Student> getRecords();

    @Insert
    void insertRecords(Student... student);


}
