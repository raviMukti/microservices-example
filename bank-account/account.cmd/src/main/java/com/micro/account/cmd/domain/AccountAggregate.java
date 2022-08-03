package com.micro.account.cmd.domain;

import com.micro.account.cmd.api.commands.OpenAccountCommand;
import com.micro.account.common.events.AccountClosedEvent;
import com.micro.account.common.events.AccountOpenedEvent;
import com.micro.account.common.events.FundsDepositEvent;
import com.micro.account.common.events.FundsWithdrawnEvent;
import com.micro.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private double balance;

    public Boolean getActive()
    {
        return this.active;
    }

    public double getBalance()
    {
        return this.balance;
    }

    public AccountAggregate(OpenAccountCommand command)
    {
        raiseEvent(AccountOpenedEvent.builder()
                    .id(command.getId())
                    .accountHolder(command.getAccountHolder())
                    .createdDate(new Date())
                    .accountType(command.getAccountType())
                    .openingBalance(command.getOpeningBalance())
                    .build());
    }

    public void apply(AccountOpenedEvent event)
    {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount)
    {
        if (!this.active)
        {
            throw new IllegalStateException("Funds Cannot Be Deposit Into a closed account");
        }
        if (amount == 0)
        {
            throw new IllegalStateException("The Deposit amount must be greater than zero");
        }
        raiseEvent(FundsDepositEvent.builder()
                    .id(this.id)
                    .amount(amount)
                    .build());
    }

    public void apply(FundsDepositEvent event)
    {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount)
    {
        if (!this.active)
        {
            throw new IllegalStateException("Funds Cannot Be Withdrawn From a closed account");
        }
        raiseEvent(FundsWithdrawnEvent.builder()
                    .id(this.id)
                    .amount(amount)
                    .build());
    }

    public void apply(FundsWithdrawnEvent event)
    {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount()
    {
        if (!this.active)
        {
            throw new IllegalStateException("The Bank Account Has Already Been Closed");
        }
        raiseEvent(AccountClosedEvent.builder()
                    .id(this.id)
                    .build());
    }

    public void apply(AccountClosedEvent event)
    {
        this.id = event.getId();
        this.active = false;
    }
}
