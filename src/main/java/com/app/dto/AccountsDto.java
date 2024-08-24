package com.app.dto;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {


    @NotNull(message = "Account number can not be empty pr null")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be empty or null")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be empty or null")
    private String branchAddress;
}
