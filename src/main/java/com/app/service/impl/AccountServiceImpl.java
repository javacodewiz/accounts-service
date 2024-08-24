package com.app.service.impl;

import com.app.constant.AccountConstants;
import com.app.dto.CustomerDetailsDto;
import com.app.mapper.CustomerDetailsMapper;
import com.app.dto.CustomerDto;
import com.app.entity.Accounts;
import com.app.entity.Customer;
import com.app.exception.CustomerAlreadyExistsException;
import com.app.exception.ResourceNotFoundException;
import com.app.mapper.CustomerMapper;
import com.app.repository.AccountRepository;
import com.app.repository.CustomerRepository;
import com.app.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;



    @Override
    public void createAccount(CustomerDto dto) {

        Optional<Customer> op = customerRepository.findByMobileNumber(dto.getMobileNumber());
        if(op.isPresent())
        {
            throw new CustomerAlreadyExistsException("customer already present with this mobile number + "+dto.getMobileNumber());

        }
        Customer customer  = CustomerMapper.mapToCustomer(new Customer(),dto);
         Customer savedAccount = customerRepository.save(customer);
         Accounts accounts = createNewAccounts(savedAccount);
         accountRepository.save(accounts);

    }

    @Override
    public CustomerDetailsDto getbyMobilenumber(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer data is not found with this mobile number : "+mobileNumber));

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).get();

        return CustomerDetailsMapper.mapToCustomerDetails(customer,accounts);

    }

    @Override
    public boolean updateAccount(CustomerDetailsDto dto) {
        boolean isUpdate = false;

        Customer customer = customerRepository.findByMobileNumber(dto.getMobileNumber())
                .orElseThrow(()->new ResourceNotFoundException("Customer data is not found with this mobilenumber : "+dto.getMobileNumber()));

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Account is not found with this customer id : "+customer.getCustomerId()));

        Accounts account = accountRepository.findById(accounts.getAccountNumber())
                        .orElseThrow(()->new ResourceNotFoundException("Account is not found with Account Number : "+accounts.getAccountNumber()));
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());
        account.setAccountType(dto.getAccountType());
        account.setBranchAddress(dto.getBranchAddress());
        customerRepository.save(customer);
        accountRepository.save(account);
        isUpdate=true;
        return isUpdate;
    }

    @Override
    public List<CustomerDetailsDto> getAllData() {


        List<Customer> customerList = customerRepository.findAll();
        List<Accounts> accountsList  = accountRepository.findAll();
        List<CustomerDetailsDto> cl= customerList.stream().map((customer)->CustomerDetailsMapper.customerMapToCustomerDetails(customer)).collect(Collectors.toList());

        List<CustomerDetailsDto> result = new ArrayList<>();

        for(Accounts ac :accountsList)
        {
            for(CustomerDetailsDto cd:cl)
            {
                if(cd.getCustomerId()==ac.getCustomerId())
                {
                    cd = CustomerDetailsMapper.accountsMapToCustomerDetails(ac,cd);
                    result.add(cd);
                }
            }
        }



    return result;
    }

    @Override
    public void deleteAccounts(String mobilenumber) {

        Customer customer = customerRepository.findByMobileNumber(mobilenumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer details is not available with mobile Number"+mobilenumber));

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Account is not found with Customer id : "+customer.getCustomerId()));

        customerRepository.delete(customer);
        accountRepository.delete(accounts);

    }


    private Accounts createNewAccounts(Customer customer)
    {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L+new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("jack");
        return  accounts;
    }

}
