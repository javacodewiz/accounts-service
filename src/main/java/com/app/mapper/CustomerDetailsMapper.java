package com.app.mapper;

import com.app.dto.CustomerDetailsDto;
import com.app.entity.Accounts;
import com.app.entity.Customer;

public class CustomerDetailsMapper {


    public static CustomerDetailsDto mapToCustomerDetails(Customer customer , Accounts accounts)
    {
        CustomerDetailsDto ct = new CustomerDetailsDto();
        ct.setName(customer.getName());
        ct.setEmail(customer.getEmail());
        ct.setMobileNumber(customer.getMobileNumber());
        ct.setAccountNumber(accounts.getAccountNumber());
        ct.setAccountType(accounts.getAccountType());
        ct.setBranchAddress(accounts.getBranchAddress());
        return  ct;
    }

    public static CustomerDetailsDto customerMapToCustomerDetails(Customer customer)
    {
        CustomerDetailsDto ct = new CustomerDetailsDto();
        ct.setName(customer.getName());
        ct.setEmail(customer.getEmail());
        ct.setMobileNumber(customer.getMobileNumber());
        ct.setCustomerId(customer.getCustomerId());
        return ct;
    }

    public static CustomerDetailsDto accountsMapToCustomerDetails(Accounts accounts,CustomerDetailsDto ct)
    {
        ct.setAccountNumber(accounts.getAccountNumber());
        ct.setAccountType(accounts.getAccountType());
        ct.setBranchAddress(accounts.getBranchAddress());
        return ct;
    }
}
