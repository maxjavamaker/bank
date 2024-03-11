package edu.yu.cs.intro.bank2023;
/**
 * A StockTransaction is immutable. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime().
 */
public class StockTransaction implements Transaction{
    private StockListing stockListing;
    private TxType type;
    private int quantity;
    private long nanoTimeStamp;

    /**
     *
     * @param listing
     * @param type
     * @param quantity
     * @throws InvalidTransactionException thrown if TxType is neither BUY nor SELL, or if quantity <= 0, or if listing == null
     */
    public StockTransaction(StockListing listing, TxType type, int quantity) throws InvalidTransactionException{
        if (listing == null){
            throw new InvalidTransactionException("Listing cannot be null", type);
        }
        if (quantity <= 0){
            throw new InvalidTransactionException("Quantity must be greater than zero", type);
        }
        if (type != TxType.BUY && type != TxType.SELL ){
            throw new InvalidTransactionException("Transaction must be buy or sell", type);
        }
        this.stockListing = listing;
        this.type = type;
        this.quantity = quantity;
        this.nanoTimeStamp = System.nanoTime();
    }
    public StockListing getStock(){
    return stockListing;
    }
    public int getQuantity(){
      return quantity;
    }
    @Override
    public TxType getType() {
        return type;
    }
    @Override
    public long getNanoTimestamp() {
       return this.nanoTimeStamp;
    }
}
