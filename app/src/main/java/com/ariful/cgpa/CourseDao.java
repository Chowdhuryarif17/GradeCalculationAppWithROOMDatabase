package com.ariful.cgpa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ariful.cgpa.model.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void InsertCourse(Course course);

    @Delete
    void DeleteCourse(Course course);

    @Update
    void UpdateCourse(Course course);

    @Query("select * from Course where semesterId like :semesterId")
    List<Course> GetCoursesBySemesterId(int semesterId);

    @Insert
    void InsertAllCourseList(List<Course> courses);

    @Query("delete from course")
    void DeleteCourse();
}
