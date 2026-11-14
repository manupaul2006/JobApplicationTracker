package com.manu.jobapplicationtracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Size(min = 2,max = 50)
    private String name;

    @NotNull
    @Size(min = 10,max = 30)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min=10,max = 30)
    private String password;
}
