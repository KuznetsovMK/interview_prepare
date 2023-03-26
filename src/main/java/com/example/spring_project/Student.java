package com.example.spring_project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "STUDENT")
public class Student {

    @Id
    private UUID id;
    @Column
    private String name;
    @Column
    private Integer age;
}

