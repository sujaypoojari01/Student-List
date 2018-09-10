package com.example.sujay.studentlist.Utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("select * from students")
    List<Student> getRecords();

    @Insert
    void insertRecords(Student... student);

    @Update
    void updateRecords(Student student);

    @Delete
    void deleteRecord (Student student);


}
