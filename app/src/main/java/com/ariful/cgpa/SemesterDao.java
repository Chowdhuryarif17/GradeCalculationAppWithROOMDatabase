package com.ariful.cgpa;
import com.ariful.cgpa.model.Semester;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface SemesterDao {

    @Insert
    void InsertSemester(Semester semester);

    @Update
    void UpdateSemester(Semester semester);

    @Delete
    void DeleteSemester(Semester semester);

    @Query("select * from semester Order by id ASC")
    List<Semester> GetAllSemesterFromSemesterDao();
}
