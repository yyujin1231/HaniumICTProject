package com.example.packpagedosirak.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private int id;

    //private String username;
    //private String password;

    //private String role;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String email;
    private String role;
    private String password;



}
