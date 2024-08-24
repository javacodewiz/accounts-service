package com.app.controller;


import com.app.constant.AccountConstants;
import com.app.dto.CustomerDetailsDto;
import com.app.dto.CustomerDto;
import com.app.dto.ResponseDto;
import com.app.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD Rest API for Account Service",
        description = "CRUD REST API for Bank Account Service"
)
public class AccountController {


    private AccountService service;

    @PostMapping("/create")
    @Operation(summary = "To create account rest api",description = "REST API for create new customer and account")
    @ApiResponse(responseCode = "201",description = "Http Status Created")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody @Valid CustomerDto dto){

        service.createAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));


    }

    @Operation(summary = "rest api for get the customer data",description = "REST API for Get Account Details using mobile number")
    @ApiResponse(responseCode = "200",description = "Http Status OK")
    @ApiResponse(responseCode = "400",description = "Http status BAD_REQUEST")
    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDetailsDto> getByMobileNumber(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digit") String mobileNumber)
    {
         return ResponseEntity.ok(service.getbyMobilenumber(mobileNumber));
    }


    @Operation(summary = "rest api for get all customers data",description = "REST API for Get all customer Details")
    @ApiResponse(responseCode = "200",description = "Http Status OK")
    @GetMapping
    public ResponseEntity<List<CustomerDetailsDto>> getAllAccounts()
    {
       return  ResponseEntity.ok(service.getAllData());
    }


    @Operation(summary = "rest api for update the customer data",description = "REST API for update customer and account Details")
    @ApiResponse(responseCode = "200",description = "Http Status OK")
    @ApiResponse(responseCode = "400",description = "Http status BAD_REQUEST")
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody @Valid CustomerDetailsDto dto)
    {
        service.updateAccount(dto);
        return  new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200),HttpStatus.OK);
    }


    @Operation(summary = "rest api for delete the customer data",description = "REST API for delete customer and  Account Details using  mobile number")
    @ApiResponse(responseCode = "200",description = "Http Status OK")
    @ApiResponse(responseCode = "400",description = "Http status BAD_REQUEST")
    @DeleteMapping("/{mobilenumber}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digit") String mobilenumber)
    {
        service.deleteAccounts(mobilenumber);
        return ResponseEntity.ok(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));

    }

}
