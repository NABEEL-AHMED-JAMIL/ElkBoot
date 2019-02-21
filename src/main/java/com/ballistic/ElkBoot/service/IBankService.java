package com.ballistic.ElkBoot.service;

import com.ballistic.ElkBoot.model.Bank;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IBankService extends ICrudService<Bank> {

    public Optional<Bank> findByAccountNumber(String accountNumber);
    public List<Bank> findByAccountNumbers(Set<String> accountNumbers);
    public List<Bank> findByFilterAIdsWithGender(Character gender, Set<String> accountNumbers);

}
