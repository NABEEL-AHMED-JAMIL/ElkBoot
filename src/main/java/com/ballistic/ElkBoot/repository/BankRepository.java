package com.ballistic.ElkBoot.repository;

import com.ballistic.ElkBoot.model.Bank;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BankRepository extends ElasticsearchRepository<Bank, String> {

    public final String findByAccountNumber = "{ \"match\": { \"accountNumber\": \"?0\" } }";
    public final String findByAccountNumbers = "{ \"bool\": { \"filter\": { \"terms\": { \"accountNumber\": ?0 } } } }";
    public final String findByFilterAIdsWithGender = "{\"bool\":{\"must\":[{\"match\":{\"gender\":\"?0\"}}],\"filter\":{\"terms\":{\"accountNumber.keyword\":[1,2,3,4,5,6,7,8,9,17,10,25,14,58]}}}}";

    @Query(findByAccountNumber)
    public Optional<Bank> findByAccountNumber(String accountNumber);

    @Query(findByAccountNumbers)
    public List<Bank> findByAccountNumbers(Set<String> accountNumbers);

    @Query(findByFilterAIdsWithGender)
    public List<Bank> findByFilterAIdsWithGender(Character gender, Set<String> accountNumbers);

}
