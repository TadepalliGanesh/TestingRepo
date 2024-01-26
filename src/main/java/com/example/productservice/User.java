package com.example.productservice;


import lombok.Data;

@Data
public class User {

    private int id;

    private String firstName;

    private String lastName;

    User(int id,String firstName,String lastName){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
