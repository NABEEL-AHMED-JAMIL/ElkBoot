package com.ballistic.ElkBoot.api;

import com.ballistic.ElkBoot.model.StockPrice;
import com.ballistic.ElkBoot.service.imp.ImpStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.ParseException;

@RestController
public class StockPriceApi {

    private String pong = "{ \"response\" : \"pong\"}";
    private String downloadUrl = "https://raw.githubusercontent.com/rrohitramsen/firehose/master/src/main/resources/data/stock_6_lac.csv";
    private String FORMAT_DATE = "d-MMMM-yyyy";

    @Autowired
    private ImpStockPriceService stockPriceService;

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String ping() { return pong; }

    @RequestMapping(value = "/save/stockPrice", method = RequestMethod.POST)
    public void save(@RequestBody StockPrice stockPrice) { this.stockPriceService.save(stockPrice); }

    @RequestMapping(value = "/save/stockPrices", method = RequestMethod.POST)
    public void save(@RequestBody List<StockPrice> stockPrices) { this.stockPriceService.save(stockPrices); }

    @RequestMapping(value = "/fetchAll/stockPrices", method = RequestMethod.GET)
    public Iterable<StockPrice> fetchAll() { return  this.stockPriceService.fetchAll(); }

    @RequestMapping(value = "/deleteAll/stockPrices", method = RequestMethod.DELETE)
    public void deleteAll() { this.stockPriceService.deleteAll(); }

    @RequestMapping(value = "/batchStart/stockPrices", method = RequestMethod.GET)
    public void startBatch() { this.openConnection(); }

    @Deprecated
    @RequestMapping(value = "/find/stockPrices/from/{from}/size/{size}", method = RequestMethod.GET)
    public Iterable<StockPrice> findBySize(@PathVariable("from") Integer from, @PathVariable("size") Integer size) {
        return this.stockPriceService.findBySize(from, size);
    }

    private void openConnection() {
        try {
            long startTime = System.currentTimeMillis();

            URL myUrl = new URL(downloadUrl);
            HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
            InputStream inputStream = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);

            List<StockPrice> stockPrices = new ArrayList<>();
            String inputLine;

            Integer counter = 0;
            Integer currentLine = 0;
            Integer loop = 0;

            while ((inputLine = br.readLine()) != null) {
                System.out.println("Current Line :- " + (currentLine) + " Value :- " + inputLine);
                if(currentLine != 0) {
                    System.out.println("+---------------------------------------------------------------------------------------------------------+");
                    StockPrice stockPrice = rawDataToPojo(inputLine);
                    System.out.println("+---------------------------------------------------------------------------------------------------------+");
                    stockPrices.add(stockPrice);
                    if(counter == 15000) {
                        System.out.println("Loop :- " + loop++  + "  Counter :- " + counter + " Total Fetch :- " + (loop * counter));
                        System.out.println("+------------------------------------------------------------------------------------------------------+");
                        this.save(stockPrices);
                        counter = 0;
                        stockPrices = new ArrayList<>();
                    }
                }
                currentLine++; counter++;
            }
            br.close();
            System.out.println("Start Time :- " + new Date(startTime) +
                ", Current Time :- " + new Date(System.currentTimeMillis()) +
                " Total Time :- " + (System.currentTimeMillis() - startTime) + ".ms");
        } catch (IOException ex) {
            System.out.println("Error :- " + ex.getLocalizedMessage());
        } catch (NullPointerException ex) {
            System.out.println("Error :- " + ex.getLocalizedMessage());
        }
    }

    private StockPrice rawDataToPojo(String inputLine) {
        long startTime = System.currentTimeMillis();
        StockPrice stockPrice = new StockPrice();
        try {
            String[] stockPrices = inputLine.split(",");
            stockPrice.setUuId(java.util.UUID.randomUUID().toString().replaceAll("-", ""));

            if(stockPrices[0] != null && !stockPrices[0].equals("")) { stockPrice.setDate(new SimpleDateFormat(FORMAT_DATE, Locale.ENGLISH).parse(stockPrices[0])); }
            if(stockPrices[1] != null && !stockPrices[1].equals("")) { stockPrice.setOpenPrice(Double.valueOf(stockPrices[1])); }
            if(stockPrices[2] != null && !stockPrices[2].equals("")) { stockPrice.setHighPrice(Double.valueOf(stockPrices[2])); }
            if(stockPrices[3] != null && !stockPrices[3].equals("")) { stockPrice.setLowPrice(Double.valueOf(stockPrices[3])); }
            if(stockPrices[4] != null && !stockPrices[4].equals("")) { stockPrice.setClosePrice(Double.valueOf(stockPrices[4])); }
            if(stockPrices[5] != null && !stockPrices[5].equals("")) { stockPrice.setWap(Double.valueOf(stockPrices[5])); }
            if(stockPrices[6] != null &&  !stockPrices[6].equals("")) { stockPrice.setNoOfShares(Integer.valueOf(stockPrices[6])); }
            if(stockPrices[7] != null && !stockPrices[7].equals("")) { stockPrice.setNoOfTrades(Integer.valueOf(stockPrices[7])); }
            if(stockPrices[8] != null && !stockPrices[8].equals("")) { stockPrice.setTotalTurnover(Double.valueOf(stockPrices[8])); }
            if(stockPrices[9] != null && !stockPrices[9].equals("")) { stockPrice.setDeliverableQuantity(Integer.valueOf(stockPrices[9])); }
            if(stockPrices[10] != null && !stockPrices[10].equals("")) { stockPrice.setDeliQtyToTradedQty(Double.valueOf(stockPrices[10])); }
            if(stockPrices[11] != null && !stockPrices[11].equals("")) { stockPrice.setSpreadHighLow(Double.valueOf(stockPrices[11])); }
            if(stockPrices[12] != null && !stockPrices[12].equals("")) { stockPrice.setSpreadCloseOpen(Double.valueOf(stockPrices[12])); }

        } catch (ParseException ex) {
            System.err.println("Error " + ex.getLocalizedMessage());
        }  catch (NumberFormatException ex) {
            System.err.println("Error " + ex.getLocalizedMessage());
        }
        System.out.println("Rwa Data   --> StockPrice       Convert Time :- " + (System.currentTimeMillis() - startTime) + ".ms");

        return stockPrice;
    }

}