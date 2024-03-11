package edu.yu.cs.intro.bank2023;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

/**
 * Models a brokerage account, i.e. an account used to buy, sell, and own stocks
 */
public class BrokerageAccount extends Account{
    private int accountNumber;
    private Patron patron;
    private double balance = 0;
    private ArrayList<StockShares> ownedStocks = new ArrayList<>();
    /**
     * This will be called by the Bank class.
     * @param accountNumber the account number assigned by the bank to this new account
     * @param patron the Patron who owns this account
     * @see Bank#openNewBrokerageAccount(Patron)
     */
    protected BrokerageAccount(int accountNumber, Patron patron) {
        super(accountNumber, patron);
    }

    /**
     * @return an unmodifiable list of all the shares of stock currently owned by this account
     * @see java.util.Collections#unmodifiableList(List)
     */
    public List<StockShares> getListOfShares(){
        return Collections.unmodifiableList(this.ownedStocks);
    }

    /**
     * If the transaction is not an instanceof StockTransaction, throw an IllegalArgumentException.
     *
     * If tx.getType() is BUY, do the following:
     *         If there aren't enough shares of the stock available for purchase, throw an InvalidTransactionException.
     *         The total amount of cash needed for the tx  = tx.getQuantity() * tx.getStock().getPrice(). If the patron doesn't have enough cash in his SavingsAccount for this transaction, throw InsufficientAssetsException.
     *         If he does have enough cash, do the following:
     *         1) reduce available share of StockListing by tx.getQuantity()
     *         2) reduce cash in patron's savings account by tx.getQuantity() * StockListing.getPrice()
     *         3)  create a new StockShare for this stock with the quantity set to tx.getQuantity() and listing set to tx.getStock() (or increase StockShare quantity, if there already is a StockShare instance in this account, by tx.getQuantity())
     *         4) add this to the set of transactions recorded in this account
     *
     * If tx.getType() is SELL, do the following:
     *          //If this account doesn't have the specified number of shares in the given stock, throw an InsufficientAssetsException.
     *          //Reduce the patron's shares in the stock by the tx.getQuantity()
     *          //The revenue from the sale = the current price per share of the stock * number of shares to be sold. Use a DEPOSIT transaction to add the revenue to the Patron's savings account.
     *
     * @param tx the transaction to execute on this account
     * @see StockTransaction
     */
    @Override
    public void executeTransaction(Transaction tx) throws InsufficientAssetsException,InvalidTransactionException {
        boolean createNewStockShares = true;
        if (!(tx instanceof StockTransaction)){
            throw new InvalidTransactionException("transaction is not of type StockTransaction", tx.getType());
        }
        StockTransaction stockTransaction = (StockTransaction) tx;
        if (stockTransaction.getType() == Transaction.TxType.BUY){
            if (stockTransaction.getStock().getAvailableShares() < stockTransaction.getQuantity()){
                throw new InvalidTransactionException("there are not enough available shares to buy to execute this transaction", stockTransaction.getType());
            }
            else if (this.getPatron().getSavingsAccount().getValue() < stockTransaction.getQuantity() * stockTransaction.getStock().getPrice()){
                throw new InsufficientAssetsException(tx, this.getPatron());
            }
            else {
                stockTransaction.getStock().reduceAvailableShares(stockTransaction.getQuantity());
                CashTransaction cashTransaction = new CashTransaction(Transaction.TxType.WITHDRAW, stockTransaction.getQuantity() * stockTransaction.getStock().getPrice());
                this.getPatron().getSavingsAccount().executeTransaction(cashTransaction);
                for (int i = 0; i < ownedStocks.size(); i++){
                    if (ownedStocks.get(i).getListing().getTickerSymbol().equals(stockTransaction.getStock().getTickerSymbol())){
                        ownedStocks.get(i).setQuantity(ownedStocks.get(i).getQuantity() + stockTransaction.getQuantity());
                        createNewStockShares = false;
                    }
                }
                if (createNewStockShares){
                    StockShares stockShares = new StockShares(stockTransaction.getStock());
                    stockShares.setQuantity(stockTransaction.getQuantity());
                    ownedStocks.add(stockShares);
                }

            }
            transactions.add(stockTransaction);
        }
        else if (stockTransaction.getType() == Transaction.TxType.SELL){
            for (int i = 0; i < ownedStocks.size(); i++){
                if (ownedStocks.get(i).getListing().getTickerSymbol().equals(stockTransaction.getStock().getTickerSymbol())){
                    if (ownedStocks.get(i).getQuantity() < stockTransaction.getQuantity()){
                        throw new InsufficientAssetsException(tx, this.getPatron());
                    }
                    else {
                        ownedStocks.get(i).setQuantity(ownedStocks.get(i).getQuantity() - stockTransaction.getQuantity());
                        CashTransaction cashTransaction = new CashTransaction(Transaction.TxType.DEPOSIT, stockTransaction.getStock().getPrice() * stockTransaction.getQuantity());
                        this.getPatron().getSavingsAccount().executeTransaction(cashTransaction);
                    }
                }
            }
            transactions.add(stockTransaction);
        }
    }

    /**
     * the value of a BrokerageAccount is calculated by adding up the values of each StockShare.
     * The value of a StockShare is calculated by multiplying the StockShare quantity by its listing's price.
     * @return
     */
    @Override
    public double getValue() {
        balance = 0;
        for (int i = 0; i < ownedStocks.size(); i++){
            balance += ownedStocks.get(i).getQuantity() * ownedStocks.get(i).getListing().getPrice();
        }
        return balance;
    }
}
