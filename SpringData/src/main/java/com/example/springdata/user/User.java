package com.example.springdata.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true) // Столбец с ограничениями
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public User() {
        // Пустой конструктор для Hibernate
    }

    // Геттеры и сеттеры (сокращены для примера)
    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}