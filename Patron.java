package edu.yu.cs.intro.bank2023;
import java.util.Objects;

/**
 * A patron (i.e. customer) of the bank, who can have up to 2 accounts - 1 savings account, 1 brokerage account.
 */
public class Patron {
    private SavingsAccount savingsAccount;
    private BrokerageAccount brokerageAccount;
    private int id;
    private Bank bank;
    private double netWorth;
    /**
     * Will be called by Bank
     * MUST be created with its SavingsAccount and BrokerageAccount being null. Accounts are to be created ONLY via the appropriate calls to the Bank class.
     * @param id account id assigned by the bank
     * @param bank
     * @see Bank#createNewPatron()
     * @see Bank#openNewSavingsAccount(Patron)
     * @see Bank#openNewBrokerageAccount(Patron)
     */
    protected Patron(int id, Bank bank){
        this.id = id;
        this.bank = bank;
     
    }
    public int getId() {
        return id;
     
    }
    public Bank getBank() {
        return bank;
    
    }

    /**
     *
     * @param savings
     * @throws ApplicationDeniedException thrown if the Patron already has a savings account
     * @see Bank#openNewSavingsAccount(Patron)
     */
    protected void setSavingsAccount(SavingsAccount savings) throws ApplicationDeniedException{
        if (this.getSavingsAccount() != null){
            throw new ApplicationDeniedException("patron already has savings account");
        }

        else {
            this.savingsAccount = savings;
        }
    }

    /**
     *
     * @param brokerage
     * @throws ApplicationDeniedException thrown if the Patron already has a BrokerageAccount OR if the Patron does NOT have a SavingsAccount
     * @see Bank#openNewBrokerageAccount(Patron)
     */
    protected void setBrokerageAccount(BrokerageAccount brokerage) throws ApplicationDeniedException{
        if (this.getBrokerageAccount() != null){
            throw new ApplicationDeniedException("patron already has a brokerage account");
        }
        else {
            this.brokerageAccount = brokerage;
        }
    }

    /**
     * @return the value of the Patron's SavingsAccount + the value of the Patron's BrokerageAccount
     * @see SavingsAccount#getValue()
     * @see BrokerageAccount#getValue()
     */
    public double getNetWorth(){
        netWorth = 0;
        if (this.getSavingsAccount() != null){
            netWorth += this.getSavingsAccount().getValue();
        }
        if (this.getBrokerageAccount() != null){
            netWorth += this.getBrokerageAccount().getValue();
        }
        return this.netWorth;

    }
    public SavingsAccount getSavingsAccount() {
        return this.savingsAccount;
      
    }
    public BrokerageAccount getBrokerageAccount() {
        return this.brokerageAccount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return this.getId() == ((Patron) obj).getId();
    }
}