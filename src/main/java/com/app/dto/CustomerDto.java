package com.app.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "Name can't be null or empty")
    @Size(min= 5,max=30,message = "Name length should be between 5 and 30")
    private String name;

    @NotEmpty(message = "email can not be empty or null")
    @Email(message = "email should be a valid email address")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digit")
    @NotEmpty(message = "Mobile Number can not be empty or null")
    private String mobileNumber;
}
