// Write your code here
package com.example.school.repository;

import java.util.*;

import com.example.school.model.Student;

public interface StudentRepository {

    ArrayList<Student> getStudentsList();

    Student getStudentById(int studentId);

    Student addStudent(Student student);

    void addStudentsList(ArrayList<Student> student);

    Student updateStudent(int studentId, Student student);

    void deleteStudent(int studentId);
}
