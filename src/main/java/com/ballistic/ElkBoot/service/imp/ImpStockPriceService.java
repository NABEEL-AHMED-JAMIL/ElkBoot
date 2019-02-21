package com.ballistic.ElkBoot.service.imp;

import com.ballistic.ElkBoot.model.StockPrice;
import com.ballistic.ElkBoot.repository.StockPriceRepository;
import com.ballistic.ElkBoot.service.IStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImpStockPriceService implements IStockPriceService {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Override
    public void save(StockPrice stockPrice) { this.stockPriceRepository.save(stockPrice); }

    @Override
    public void save(List<StockPrice> stockPrices) { this.stockPriceRepository.saveAll(stockPrices); }

    @Override
    public StockPrice update(StockPrice stockPrice) { return this.stockPriceRepository.save(stockPrice); }

    @Override
    public Iterable<StockPrice> update(List<StockPrice> stockPrices) { return this.stockPriceRepository.saveAll(stockPrices); }

    @Override
    public Iterable<StockPrice> fetchAll() { return this.stockPriceRepository.findAll(); }

    @Override
    public Optional<StockPrice> findById(String uuId) { return this.stockPriceRepository.findById(uuId); }

    @Override
    public void deleteAll() { this.stockPriceRepository.deleteAll(); }

    @Override
    public Iterable<StockPrice> findBySize(Integer from, Integer size) { return this.stockPriceRepository.findBySize(from, size); }
}
