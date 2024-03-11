package edu.yu.cs.intro.bank2023;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class StockExchange {
    private HashMap<String, StockListing> listOfStocks = new HashMap();

     protected StockExchange(){
    
    }

    /**
     *
     * @param tickerSymbol symbol of the new stock to be created, e.g. "IBM", "GOOG", etc.
     * @param initialPrice price of a single share of the stock
     * @param availableShares how many shares of the stock are available initially
     * @throws IllegalArgumentException if there's already a listing with that tickerSymbol
     */
    public void createNewListing(String tickerSymbol, double initialPrice, int availableShares){
        if(listOfStocks.containsKey(tickerSymbol)){
                throw new IllegalArgumentException();
        }
        StockListing stock = new StockListing(tickerSymbol, initialPrice, availableShares);
        listOfStocks.put(tickerSymbol,stock);
    }

    /**
     * @param tickerSymbol
     * @return the StockListing object for the given tickerSymbol, or null if there is none
     */
    public StockListing getStockListing(String tickerSymbol){
        if (listOfStocks.containsKey(tickerSymbol)){
            return listOfStocks.get(tickerSymbol);
        }
        return null;
     
    }

    /**
     * @return an umodifiable list of all the StockListings currently found on this exchange
     * @see java.util.Collections#unmodifiableList(List)
     */
    public List<StockListing> getAllCurrentListings(){
        return Collections.unmodifiableList(new ArrayList<>(listOfStocks.values()));
    }
}