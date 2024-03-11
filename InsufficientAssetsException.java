package edu.yu.cs.intro.bank2023;

public class InsufficientAssetsException extends Exception{
    private Transaction transaction;
    private Patron patron;

    public InsufficientAssetsException(Transaction tx, Patron p){
        super();
        this.transaction = tx;
        this.patron = p;
    }

    public Transaction getTx() {
        return transaction;
    }

    public Patron getPatron() {
        return patron;
    }
}