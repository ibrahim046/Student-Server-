package com.ibrahim.studentServer.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ibrahim Cherif
 * @email ibrahimcherif1@outlook.fr
 */

@Table(name = "Table_Etudiant")
@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Student_Id", updatable = false)
    @Basic(optional = false)
    private Integer studentId;
    @Column(name = "Student_Name", length = 255)
    @NotBlank(message = "Name should not be null")
    private String name;
    @Column(name = "Student_First_Name", length = 255)
    @NotBlank(message = "FirstName should not be null")
    private String firstName;
    @Column(name = "Student_age", length = 3)
    private Integer age;
    @Column(name = "Student_email", length = 100)
    @NotBlank(message = "Email should not be null")
    @Email(message = "please enter a valid email adress")
    private String email;
    @Column(name = "Student_bithdate", length = 50)
    @NotNull(message = "Birthdate should not be null")
    private Date birthDate;
    @Column(name = "Student_birth_place", length = 255)
    @NotBlank(message = "Place of birth should not be null")
    private String placeOfBirth;
    @Column(name = "Student_phone_number")
    @NotBlank(message = "The phone number should not be null")
    private String phoneNumber;
    @Column(name = "Student_status", nullable = false)
    private short status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created_on", updatable = false)
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Last_update_on")
    private Date lastUpdateOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Adress_id")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "Departement_id")
    private Departement departement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

}
