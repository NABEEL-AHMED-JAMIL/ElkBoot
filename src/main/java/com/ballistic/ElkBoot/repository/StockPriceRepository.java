package com.ballistic.ElkBoot.repository;

import com.ballistic.ElkBoot.model.StockPrice;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockPriceRepository extends ElasticsearchRepository<StockPrice, String> {

    @Query("{\"query\":{\"match_all\":{}},\"from\":?0,\"size\":?1,\"sort\":{\"highPrice\":{\"order\":\"desc\"}}}")
    public Iterable<StockPrice> findBySize(Integer from,Integer size);

}
