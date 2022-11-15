public class Account {

    private int balance;
    
    // Constructor method.
    public Account() {
        balance = 0;
    } // End of method.    


    // This method returns the amount of money in the account.
    //
    public int getBalance() {
        return balance;
    } // End of method.   


    // This method adds up the money into the account.
    //
    public void deposit(int amount) {
        balance += amount;
    } // End of method.   


    // This method reduces the money of the account.
    //
    public void withdraw(int amount) {
        balance -= amount;
    } // End of method.

}
