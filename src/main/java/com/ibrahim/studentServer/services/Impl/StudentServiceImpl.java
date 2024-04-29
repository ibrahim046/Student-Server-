package com.ibrahim.studentServer.services.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ibrahim.studentServer.beans.Student;
import com.ibrahim.studentServer.exception.InvalidInputException;
import com.ibrahim.studentServer.exception.NotFoundException;
import com.ibrahim.studentServer.repositories.StudentRepository;
import com.ibrahim.studentServer.services.StudentService;
import com.ibrahim.studentServer.util.Constants;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Direction.ASC, "studentId");
        return studentRepository.findAll(pages).getContent();

    }

    @Override
    public Student getStudentById(Integer studentId) throws NotFoundException {
        Student studentToFound = studentRepository.findById(studentId).get();
        if (studentToFound != null) {
            return studentToFound;
        }
        throw new NotFoundException("The student you are looking for does not exits");

    }

    @Override
    public Student createStudent(Student student) throws InvalidInputException {
        if (student != null) {
            if (studentRepository.findByEmail(student.getEmail()) == null) {
                student.setCreatedOn(new Date());
                student.setLastUpdateOn(new Date());
                student.setStatus(Constants.STATE_ACTIVATED);
                studentRepository.save(student);
                return student;
            } else {
                throw new InvalidInputException("The email allready esist !");
            }
        } else {
            throw new InvalidInputException("The given student can not be null ! ");
        }
    }

    @Override
    public Student updateStudent(Student student, Integer studentId) throws InvalidInputException, NotFoundException {
        Student studentToUpdate = this.getStudentById(studentId);
        if (studentToUpdate != null) {
            studentToUpdate.setName(student.getName());
            studentToUpdate.setFirstName(student.getFirstName());
            studentToUpdate.setAge(student.getAge());
            studentToUpdate.setBirthDate(student.getBirthDate());
            studentToUpdate.setPlaceOfBirth(student.getPlaceOfBirth());
            studentToUpdate.setPhoneNumber(student.getPhoneNumber());
            studentToUpdate.setLastUpdateOn(new Date());
            studentRepository.save(studentToUpdate);
            return studentToUpdate;
        } else {
            throw new NotFoundException("The student you want to update does not exits !");
        }

    }

    @Override
    public Integer deleteStudent(Integer studentId) throws InvalidInputException, NotFoundException {
        Student studentToDelete = this.getStudentById(studentId);
        if (studentToDelete != null) {
            studentToDelete.setLastUpdateOn(new Date());
            studentToDelete.setStatus(Constants.STATE_DELETED);
            studentRepository.save(studentToDelete);
            return studentId;
        } else {
            throw new NotFoundException("The student you want to delete does not exits !");
        }
    }

    @Override
    public List<Student> getStudentByName(String name) throws NotFoundException {
        if (!name.isEmpty()) {
            Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
            return studentRepository.findByNameContaining(name, sort);
        } else {
            throw new NotFoundException("Please enter a keyword !");

        }
    }

    @Override
    public List<Student> getAllDeletedStudent(short status) throws NotFoundException {
        List<Student> deletedStudent = studentRepository.getAllDeletedStudent(status);
        if (!deletedStudent.isEmpty()) {
            return deletedStudent;
        }
        throw new NotFoundException("No deleted student found in the database");
    }

}
