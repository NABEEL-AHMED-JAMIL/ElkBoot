package com.ballistic.ElkBoot.service;

import com.ballistic.ElkBoot.model.StockPrice;

public interface IStockPriceService extends ICrudService<StockPrice> {

    public Iterable<StockPrice> findBySize(Integer from,Integer size);
}
