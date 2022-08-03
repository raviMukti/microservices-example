package com.micro.account.query.api.controllers;

import com.micro.account.query.api.dto.AccountLookupResponse;
import com.micro.account.query.api.dto.EqualityType;
import com.micro.account.query.api.queries.FindAccountByHolderQuery;
import com.micro.account.query.api.queries.FindAccountByIdQuery;
import com.micro.account.query.api.queries.FindAccountWithBalanceQuery;
import com.micro.account.query.api.queries.FindAllAccountQuery;
import com.micro.account.query.domain.BankAccount;
import com.micro.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/bankAccountLookup")
public class AccountLookupController {

    private final Logger logger = Logger.getLogger(AccountLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "/")
    public ResponseEntity<AccountLookupResponse> getAllAccounts()
    {
        try
        {
            List<BankAccount> accounts = queryDispatcher.send(new FindAllAccountQuery());
            if (accounts.size() == 0)
            {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Successfully Returned {0} bank account(s)", accounts.size())).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e)
        {
            var safeErrorMessage = "Failed To Complete Get All Accounts Request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id")String id)
    {
        try
        {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByIdQuery(id));
            if (accounts.size() == 0)
            {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully Returned bank account").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e)
        {
            var safeErrorMessage = "Failed To Complete Get Accounts By Id Request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byHolder/{accountHolder}")
    public ResponseEntity<AccountLookupResponse> getAccountByHolder(@PathVariable(value = "accountHolder")String accountHolder)
    {
        try
        {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByHolderQuery(accountHolder));
            if (accounts.size() == 0)
            {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully Returned bank account").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e)
        {
            var safeErrorMessage = "Failed To Complete Get Accounts By Holder Request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/withBalance/{equalityType}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountWithBalance(@PathVariable(value = "equalityType") EqualityType equalityType, @PathVariable(value = "balance")double balance)
    {
        try
        {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountWithBalanceQuery(equalityType, balance));
            if (accounts.size() == 0)
            {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Successfully Returned {0} bank account(s)", accounts.size())).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e)
        {
            var safeErrorMessage = "Failed To Complete Get Accounts With Balance Request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
