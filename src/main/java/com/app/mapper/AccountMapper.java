package com.app.mapper;

import com.app.dto.AccountsDto;
import com.app.entity.Accounts;

public class AccountMapper {

    public static AccountsDto mapToAccountDto(Accounts accounts ,AccountsDto dto)
    {
       dto.setAccountNumber(accounts.getAccountNumber());
       dto.setAccountType(accounts.getAccountType());
       dto.setBranchAddress(accounts.getBranchAddress());
       return dto;
    }

    public static Accounts mapToAccounts(Accounts accounts ,AccountsDto dto)
    {
        accounts.setAccountNumber(dto.getAccountNumber());
        accounts.setAccountType(dto.getAccountType());
        accounts.setBranchAddress(dto.getBranchAddress());
        return accounts;
    }
}
