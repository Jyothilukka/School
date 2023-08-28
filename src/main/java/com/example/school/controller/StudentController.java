/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
package com.example.school.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

@RestController
public class StudentController{
    @Autowired
    public StudentH2Service studentservice;

    @GetMapping("/students")
    public ArrayList<Student> getStudentsList(){
        return studentservice.getStudentsList();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentservice.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return studentservice.addStudent(student);

    }

    @PostMapping("/students/bulk")
    public void addStudentsList(@RequestBody ArrayList<Student> student){
        studentservice.addStudentsList(student);
        System.out.printf("Successfully added %d students",student.size());
        
        
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student){
        return studentservice.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentservice.deleteStudent(studentId);
    }

    
}