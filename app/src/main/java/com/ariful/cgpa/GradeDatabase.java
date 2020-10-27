package com.ariful.cgpa;

import android.content.Context;

import com.ariful.cgpa.model.Course;
import com.ariful.cgpa.model.Semester;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Course.class, Semester.class}, version = 1, exportSchema = false)
public abstract class GradeDatabase extends RoomDatabase {

    public abstract CourseDao courseDao();
    public abstract SemesterDao semesterDao();

    public static volatile GradeDatabase INSTANCE;

    static GradeDatabase getDatabase(final Context context){
        if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),GradeDatabase.class,
                        "GADEDATABASE").fallbackToDestructiveMigration()
                        .build();
        }
        return INSTANCE;
    }

}
