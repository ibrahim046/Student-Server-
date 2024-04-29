package com.ibrahim.studentServer.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibrahim.studentServer.beans.Departement;
import com.ibrahim.studentServer.beans.Student;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

    Departement findByName(String name);

    @Query("SELECT d.studentList FROM Departement d WHERE d.name = :departmentName ")
    List<Student> findStudentByDepartementName(@Param("departmentName") String departmentName, Sort sort);

}
