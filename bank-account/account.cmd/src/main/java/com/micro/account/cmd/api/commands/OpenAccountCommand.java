package com.micro.account.cmd.api.commands;

import com.micro.account.common.dto.AccountType;
import com.micro.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
