package com.ibrahim.studentServer.services;

import java.util.List;

import com.ibrahim.studentServer.beans.Departement;
import com.ibrahim.studentServer.beans.Student;
import com.ibrahim.studentServer.exception.InvalidInputException;
import com.ibrahim.studentServer.exception.NotFoundException;

public interface DepartementService {

    public List<Departement> getAllDepartement(int pageNumber, int pageSize);

    public Departement getDepartementById(Integer departementId) throws NotFoundException;

    public Departement createDepartement(Departement departement) throws InvalidInputException;

    public Departement updateDepartement(Departement departement, Integer departementId)
            throws InvalidInputException, NotFoundException;

    public Integer deleteDepartement(Integer departementId) throws InvalidInputException, NotFoundException;

    public List<Departement> getDepartementByName(String name) throws NotFoundException;

    public List<Departement> getAllDeletedDepartement(short status) throws NotFoundException;

    public List<Student> getStudentByDepartementName(String name) throws NotFoundException;
}
