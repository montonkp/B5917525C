package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeID;
    private String username;
    private String password;
    @Size(min = 2, max = 30)
    private String firstName;
    @Size(min = 2, max = 30)
    private String lastName;
    @Size(min = 13,max = 13)
    private String idNumber;
    private  String email ;
    private LocalDate birthday;

//    @NotNull
    @Size(min = 5, max = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "addressID", nullable = false)
    private Address address;


    @ManyToOne
    @JoinColumn(name = "GENDER_ID")
    private Gender gender;

    public Employee(String username, String password, @Size(min = 2, max = 30) String firstName, @Size(min = 2, max = 30) String lastName, @Size(min = 13, max = 13) String idNumber, String email, LocalDate birthday,@Size(min = 5, max = 20) String phone, Address address, Gender gender) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
