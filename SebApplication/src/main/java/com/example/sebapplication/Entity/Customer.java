package com.example.sebapplication.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Setter @Getter
    private String name;
    @Setter @Getter
    private String surname;
    @Setter @Getter
    private String birthDate;
    @Setter @Getter
    private String telephoneNumber;
    @Setter @Getter
    private String email;
}