package com.ibrahim.studentServer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahim.studentServer.beans.Student;
import com.ibrahim.studentServer.exception.InvalidInputException;
import com.ibrahim.studentServer.exception.NotFoundException;
import com.ibrahim.studentServer.repositories.StudentRepository;
import com.ibrahim.studentServer.services.StudentService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllstudent(@RequestParam Integer pageNumber, @RequestParam Integer pageSize)
            throws NotFoundException {
        List<Student> studentList = studentService.getAllStudent(pageNumber, pageSize);
        if (!studentList.isEmpty()) {
            return new ResponseEntity<List<Student>>(studentList, HttpStatus.FOUND);
        }
        throw new NotFoundException("No student in the database !");

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getSingleStudent(@PathVariable Integer studentId) throws NotFoundException {
        Student studentToFound = studentService.getStudentById(studentId);
        if (studentToFound != null) {
            return new ResponseEntity<Student>(studentToFound, HttpStatus.FOUND);
        }
        throw new NotFoundException("No student not found !");

    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student)
            throws NotFoundException, InvalidInputException {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student, @RequestParam Integer studentId)
            throws NotFoundException, InvalidInputException {
        return new ResponseEntity<Student>(studentService.updateStudent(student, studentId), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{studentId}")
    public ResponseEntity<Integer> delete(@PathVariable Integer studentId)
            throws NotFoundException, InvalidInputException {

        return new ResponseEntity<Integer>(studentService.deleteStudent(studentId), HttpStatus.NO_CONTENT);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> findStudentByName(@RequestParam String name) throws NotFoundException {
        return new ResponseEntity<List<Student>>(studentService.getStudentByName(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllDeletedStudent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllDeletedStudent(@RequestParam short status) throws NotFoundException {
        return new ResponseEntity<List<Student>>(studentService.getAllDeletedStudent(status), HttpStatus.OK);
    }

}
