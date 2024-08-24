package com.app.service;

import com.app.dto.CustomerDetailsDto;
import com.app.dto.CustomerDto;
import com.app.entity.Customer;

import java.util.List;

public interface AccountService {

    public void createAccount(CustomerDto dto);

    public CustomerDetailsDto getbyMobilenumber(String mobileNumber);

    public boolean updateAccount(CustomerDetailsDto dto);

    public List<CustomerDetailsDto> getAllData();

    public void deleteAccounts(String mobilenumber);
}
