package com.app.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsDto {

    @NotEmpty(message = "Name can't be null or empty")
    @Size(min= 5,max=30,message = "Name length should be between 5 and 30")
    private String name;

    @NotEmpty(message = "email can not be empty or null")
    @Email(message = "email should be a valid email address")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digit")
    @NotEmpty(message = "Mobile Number can not be empty or null")
    private String mobileNumber;

    @NotNull(message = "Account number can not be empty pr null")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be empty or null")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be empty or null")
    private String branchAddress;


    private Long customerId;

    public CustomerDetailsDto(String name, String email, String mobileNumber, String accountType, String branchAddress) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }
}
