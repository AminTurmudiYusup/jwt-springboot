package com.example.springjwt.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Client {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String userName;
    private String password;
}
