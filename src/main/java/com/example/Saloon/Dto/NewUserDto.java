package com.example.Saloon.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserDto {

    private int id;

    private String role;

    @NotBlank
    @Size(min = 2, message = "UserName should have atleast 2 characters!")
    private String username;

    @NotBlank
    @Size(min = 6, message = "Password should have atleast 6 characters!")
    private String password;

    @NotBlank(message = "full name cannot be blank!")
    private String fullName;

    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank!")
    @Email
    private String email;
    private String salonName;
    private String salonBranch;
    private String salonCity;
    private String pinCode;

}
