package com.ballistic.ElkBoot.api;

import com.ballistic.ElkBoot.model.Bank;
import com.ballistic.ElkBoot.service.imp.ImpBankService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class BankApi {

    private String downloadUrl = "C://Users//AdMaxim//Desktop//bankAccount.txt";

    @Autowired
    private ImpBankService bankService;

    @RequestMapping(value = "/save/bank", method = RequestMethod.POST)
    public void save(@RequestBody Bank bank) { this.bankService.save(bank); }

    @RequestMapping(value = "/save/banks", method = RequestMethod.POST)
    public void save(@RequestBody List<Bank> banks) { this.bankService.save(banks); }

    @RequestMapping(value = "/fetchAll/banks", method = RequestMethod.GET)
    public Iterable<Bank> fetchAll() { return  this.bankService.fetchAll(); }

    @RequestMapping(value = "/deleteAll/banks", method = RequestMethod.DELETE)
    public void deleteAll() { this.bankService.deleteAll(); }

    @Deprecated
    @RequestMapping(value = "/batchStart/bank", method = RequestMethod.GET)
    public void startBatch() { this.openConnection(); }

    @RequestMapping(value = "/_search/banks/_aId/{accountNumber}", method = RequestMethod.GET)
    public Optional<Bank> findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return this.bankService.findByAccountNumber(accountNumber);
    }

    @RequestMapping(value = "/_search/banks/_aIds/{accountNumbers}", method = RequestMethod.GET)
    public List<Bank> findByAccountNumbers(@PathVariable("accountNumbers") Set<String> accountNumbers) {
        return this.bankService.findByAccountNumbers(accountNumbers);
    }

    @RequestMapping(value = "/_search/banks/gn/{gender}/_aIds/{accountNumbers}" ,method = RequestMethod.GET)
    public List<Bank> findByFilterAIdsWithGender(
        @PathVariable("gender") Character gender, @PathVariable("accountNumbers") Set<String> accountNumbers){
        return this.bankService.findByFilterAIdsWithGender(gender,accountNumbers);
    }

    private void openConnection() {

        try (BufferedReader br = new BufferedReader(new FileReader(downloadUrl))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) { builder.append(line); }

            Gson gson = new Gson();
            Type type = new TypeToken<List<Bank>>() {}.getType();
            this.save((List<Bank>)gson.fromJson(builder.toString(), type));
            System.out.println("Success Fully Save");

        } catch (IOException ex) {
            System.out.println("Error :- " + ex.getLocalizedMessage());
        }
    }

}