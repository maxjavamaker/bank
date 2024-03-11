package edu.yu.cs.intro.bank2023;
import java.util.ArrayList;
import java.util.Collections;

public class SavingsAccount extends Account{
    private int accountNumber;
    private Patron patron;
    private double balance = 0;
   
    protected SavingsAccount(int accountNumber, Patron patron) {
        super(accountNumber, patron);
  
    }

    /**
     * for a DEPOSIT transaction: increase the balance by transaction amount
     * for a WITHDRAW transaction: decrease the balance by transaction amount
     * add the transaction to the transaction history of this account
     * @param tx
     * @return
     * @throws IllegalArgumentException thrown if tx is not a CashTransaction
     */
    @Override
    public void executeTransaction(Transaction tx) throws InsufficientAssetsException,InvalidTransactionException {
        if (!(tx instanceof CashTransaction)){
            throw new InvalidTransactionException("must be a cash transaction", tx.getType());
        }
        CashTransaction cashTransaction = (CashTransaction) tx;
        transactions.add(cashTransaction);
        if (cashTransaction.getType().equals(Transaction.TxType.DEPOSIT)){   
            balance += cashTransaction.getAmount();
        }
        else if (cashTransaction.getType().equals(Transaction.TxType.WITHDRAW)){
            balance -= cashTransaction.getAmount();
        }
    }

    /**
     * @return the account's balance
     */
    @Override
    public double getValue() {
        return this.balance;

    }
}