package com.ibrahim.studentServer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahim.studentServer.beans.Departement;
import com.ibrahim.studentServer.beans.Student;
import com.ibrahim.studentServer.exception.InvalidInputException;
import com.ibrahim.studentServer.exception.NotFoundException;
import com.ibrahim.studentServer.services.DepartementService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/departement")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Departement> createDepartement(@Valid @RequestBody Departement departement)
            throws InvalidInputException {
        return new ResponseEntity<Departement>(departementService.createDepartement(departement), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStudentByDepartmentName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getStudentByDepartementName(@Valid @RequestParam String departementName)
            throws NotFoundException {
        return new ResponseEntity<List<Student>>(departementService.getStudentByDepartementName(departementName),
                HttpStatus.OK);
    }
}
