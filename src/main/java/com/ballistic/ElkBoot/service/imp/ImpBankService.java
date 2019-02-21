package com.ballistic.ElkBoot.service.imp;

import com.ballistic.ElkBoot.model.Bank;
import com.ballistic.ElkBoot.repository.BankRepository;
import com.ballistic.ElkBoot.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ImpBankService implements IBankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public void save(Bank bank) { this.bankRepository.save(bank); }

    @Override
    public void save(List<Bank> banks) { this.bankRepository.saveAll(banks); }

    @Override
    public Bank update(Bank bank) { return this.bankRepository.save(bank); }

    @Override
    public Iterable<Bank> update(List<Bank> banks) { return this.bankRepository.saveAll(banks); }

    @Override
    public Iterable<Bank> fetchAll() { return this.bankRepository.findAll(); }

    @Override
    public Optional<Bank> findById(String uuId) { return this.bankRepository.findById(uuId); }

    @Override
    public void deleteAll() { this.bankRepository.deleteAll(); }

    @Override
    public Optional<Bank> findByAccountNumber(String accountNumber) { return this.bankRepository.findByAccountNumber(accountNumber); }

    @Override
    public List<Bank> findByAccountNumbers(Set<String> accountNumbers) { return this.bankRepository.findByAccountNumbers(accountNumbers); }

    @Override
    public List<Bank> findByFilterAIdsWithGender(Character gender, Set<String> accountNumbers) {
        return this.bankRepository.findByFilterAIdsWithGender(gender, accountNumbers);
    }
}
