package edu.yu.cs.intro.bank2023;

/**
 * A CashTransaction is immutable. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime().
 */
public class CashTransaction implements Transaction{
    private TxType type;
    private double amount;
    private final Long nanoTimeStamp;

    /**
     *
     * @param type
     * @param amount
     * @throws InvalidTransactionException thrown if type is neither DEPOSIT nor WITHDRAW, or if amount <= 0
     */
    public CashTransaction(TxType type, double amount) throws InvalidTransactionException{
        if (amount <= 0 || (type != TxType.DEPOSIT && type != TxType.WITHDRAW)){
            throw new InvalidTransactionException("cash transaction amount is less than zero or is not type DEPOSIT or type WITHDRAW", type);
        }
        this.type = type;
        this.amount = amount;
        this.nanoTimeStamp = System.nanoTime();

    }

    public double getAmount(){
        return this.amount;
    }
    @Override
    public TxType getType() {
        return this.type;
    }
    @Override
    public long getNanoTimestamp() {
        return this.nanoTimeStamp;
    }
}