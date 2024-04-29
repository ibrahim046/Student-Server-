package com.ibrahim.studentServer.services;

import java.util.List;

import com.ibrahim.studentServer.beans.Student;
import com.ibrahim.studentServer.exception.InvalidInputException;
import com.ibrahim.studentServer.exception.NotFoundException;

public interface StudentService {

    public List<Student> getAllStudent(int pageNumber, int pageSize);

    public Student getStudentById(Integer studentId) throws NotFoundException;

    public Student createStudent(Student student) throws InvalidInputException;

    public Student updateStudent(Student student, Integer studentId) throws InvalidInputException, NotFoundException;

    public Integer deleteStudent(Integer studentId) throws InvalidInputException, NotFoundException;

    public List<Student> getStudentByName(String name) throws NotFoundException;

    public List<Student> getAllDeletedStudent(short status) throws NotFoundException;

}
