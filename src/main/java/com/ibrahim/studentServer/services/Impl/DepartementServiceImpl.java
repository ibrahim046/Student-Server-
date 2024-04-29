package com.ibrahim.studentServer.services.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ibrahim.studentServer.beans.Departement;
import com.ibrahim.studentServer.beans.Student;
import com.ibrahim.studentServer.exception.InvalidInputException;
import com.ibrahim.studentServer.exception.NotFoundException;
import com.ibrahim.studentServer.repositories.DepartementRepository;
import com.ibrahim.studentServer.services.DepartementService;
import com.ibrahim.studentServer.util.Constants;

@Service
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    @Override
    public List<Departement> getAllDepartement(int pageNumber, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllDepartement'");
    }

    @Override
    public Departement getDepartementById(Integer departementId) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartementById'");
    }

    @Override
    public Departement createDepartement(Departement departement) throws InvalidInputException {
        if (departement != null) {
            if (departementRepository.findByName(departement.getName()) == null) {
                departement.setCreatedOn(new Date());
                departement.setLastUpdateOn(new Date());
                departement.setStatus(Constants.STATE_ACTIVATED);
                departementRepository.save(departement);
                return departement;
            } else {
                throw new InvalidInputException("The name already esist !");
            }
        } else {
            throw new InvalidInputException("The given departement can not be null ! ");
        }
    }

    @Override
    public Departement updateDepartement(Departement departement, Integer departementId)
            throws InvalidInputException, NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDepartement'");
    }

    @Override
    public Integer deleteDepartement(Integer departementId) throws InvalidInputException, NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDepartement'");
    }

    @Override
    public List<Departement> getDepartementByName(String name) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartementByName'");
    }

    @Override
    public List<Departement> getAllDeletedDepartement(short status) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllDeletedDepartement'");
    }

    @Override
    public List<Student> getStudentByDepartementName(String departementName) throws NotFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "departementId");
        List<Student> students = departementRepository.findStudentByDepartementName(departementName, sort);
        if (!students.isEmpty()) {
            return students;
        } else {
            throw new NotFoundException("No student found in the department" + " " + departementName);
        }
    }

}
