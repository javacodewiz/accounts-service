package com.app.mapper;

import com.app.dto.CustomerDto;
import com.app.entity.Customer;

public class CustomerMapper {


    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto dto)
    {
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setMobileNumber(customer.getMobileNumber());
        return dto;
    }

    public static Customer mapToCustomer(Customer customer,CustomerDto dto)
    {
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());
        return customer;
    }
}
