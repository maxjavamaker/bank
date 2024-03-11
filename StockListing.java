package edu.yu.cs.intro.bank2023;

/**
 * represents the stock of a single company that is listed on the StockExchange
 */
public class StockListing {
    private final String tickerSymbol;
    private final double initialPrice;
    private double currentPrice;
    private int availableShares;

    /**
     *
     * @param tickerSymbol
     * @param initialPrice
     * @param availableShares
     * @throws IllegalArgumentException if the tickerSymbol is null or empty, if the initial price is <= 0, of if availableShares <= 0
     */
    protected StockListing(String tickerSymbol, double initialPrice, int availableShares){
        if (tickerSymbol == null|| tickerSymbol.isEmpty() || initialPrice <= 0 || availableShares <= 0){
            throw new IllegalArgumentException();
        }
        this.tickerSymbol = tickerSymbol;
        this.initialPrice = initialPrice;
        this.currentPrice = initialPrice;
        this.availableShares = availableShares;
    }

    public String getTickerSymbol() {
    return tickerSymbol;
    }
    public double getPrice() {
        return currentPrice;
      
    }
    public int getAvailableShares() {
       return availableShares;
    }

    /**
     * set the price for a single share of this stock
     * @param price
     */
    protected void setPrice(double price) {
        this.currentPrice = price;
    }
    /**
     * increase the number of shares available
     * @param availableShares
     * @return the total number of shares after adding availableShares
     * @throws IllegalArgumentException if availableShares <= 0
     */
    protected int addAvailableShares(int availableShares) {
        if (availableShares <= 0){
            throw new IllegalArgumentException();
        }
        this.availableShares += availableShares;
        return availableShares;
    }
    /**
     * reduce the number of shares available
     * @param quantityToSubtract
     * @return the total number of shares after reducing availableShares
     * @throws IllegalArgumentException if quantityToSubtract > the number of available shares
     */
    protected int reduceAvailableShares(int quantityToSubtract){
        if (quantityToSubtract > this.availableShares){
            throw new IllegalArgumentException();
        }
        else {
            availableShares -= quantityToSubtract;
            return availableShares;
        }
    }
}