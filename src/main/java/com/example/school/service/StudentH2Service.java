/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import org.springframework.web.bind.annotation.*;

import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;
import com.example.school.model.Student;

@Service
public class StudentH2Service implements StudentRepository{
    @Autowired
    private JdbcTemplate db;

    @Override 
    public ArrayList<Student> getStudentsList(){
        List<Student> studentList = db.query("SELECT * FROM STUDENT", new StudentRowMapper());
        ArrayList<Student> student = new ArrayList<>(studentList);
        return student;
    }

    @Override
    public Student getStudentById(int studentId){
        try{
        Student student = db.queryForObject("SELECT * FROM STUDENT WHERE STUDENTID = ?", new StudentRowMapper(), studentId);
        return student;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student addStudent(Student student){
        db.update("INSERT INTO STUDENT(STUDENTNAME, GENDER, STANDARD) VALUES(?, ?, ?)", student.getStudentName(), student.getGender(), student.getStandard());
        Student savedStudent = db.queryForObject("SELECT * FROM STUDENT WHERE STUDENTNAME = ? AND GENDER = ? AND STANDARD = ?", 
                                 new StudentRowMapper(), student.getStudentName(), student.getGender(), student.getStandard());
        return savedStudent;                         

    }

    @Override 
    public void addStudentsList(ArrayList<Student> student){
       db.update("INSERT INTO STUDENT(STUDENTNAME, GENDER, STANDARD) VALUES(?, ?, ?)");
       List<Student> ArgsList = new ArrayList<Student>();
       
       for(Student s : student){
            Student savedStudent = db.queryForObject("SELECT * FROM STUDENT WHERE STUDENTNAME = ? AND GENDER = ? AND STANDARD = ?", 
                                 new StudentRowMapper(), s.getStudentName(), s.getGender(), s.getStandard());     
            ArgsList.add(savedStudent);                     
        }
        
    }    


    @Override
    public Student updateStudent(int studentId, Student student){
        if(student.getStudentName() != null){
            db.update("UPDATE STUDENT SET STUDENTNAME = ? WHERE STUDENTID = ?", student.getStudentName(), studentId);
        }
        if(student.getGender() != null){
            db.update("UPDATE STUDENT SET GENDER = ? WHERE STUDENTID = ?", student.getGender(), studentId);
        }
        if(student.getStandard() != 0){
            db.update("UPDATE STUDENT SET STANDARD = ? WHERE STUDENTID = ?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId){
        db.update("DELETE FROM STUDENT WHERE STUDENTID = ?", studentId);
    }

}