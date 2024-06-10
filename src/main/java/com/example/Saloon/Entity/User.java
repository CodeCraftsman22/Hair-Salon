package com.example.Saloon.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", nullable = false, unique = true)
    @Size(min=2,message = "userName should have at least 2 characters!")
    @NotBlank(message = "username cannot be blank")
    @NotNull(message = "username cannot be null")
    private String username;

    @Size(min = 6, message = "password should have at least 6 characters!")
    @NotBlank(message = "password cannot be blank")
    @NotNull(message = "password cannot be null")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "full name cannot be blank")
    @NotNull(message = "full name cannot be null")
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "email cannot be null")
    @Column(name = "email")
    private String email;

    private String role;

    private String salonName;
    private String salonBranch;
    private String salonCity;
    private String pinCode;
}
