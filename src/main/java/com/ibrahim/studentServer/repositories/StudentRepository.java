package com.ibrahim.studentServer.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibrahim.studentServer.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);

    List<Student> findByNameContaining(String name, Sort sort);

    @Query("SELECT s FROM Student s WHERE s.status = :stateDeleted")
    List<Student> getAllDeletedStudent(@Param("stateDeleted") short stateDeleted);

}
