import java.util.ArrayList;

public class User {
    private Account account;
    private ArrayList<Asset> userAssetList;
    
    // Constructor method.
    //
    public User() {
        account = new Account();
        userAssetList = new ArrayList<Asset>();
    }


    // This method returns the amount of money in the user's account.
    //
    public int getBalance() {
        return account.getBalance();
    } // End of method.   


    // This method add up the money into the user's accnout.
    //
    public void deposit(int amount) {
        account.deposit(amount);
    } // End of method.


    // This method subtract the money from the user's account.
    //
    public void withdraw(int amount) {
        account.withdraw(amount);
    } // End of method.


    // This method allows the user to sell the Shares in the portfolio.
    // 
    public <T> boolean sellShares(T share, int amount) {
        int userIndex = getIndex(userAssetList, ((Asset) share).getAssetName());
        if ((userIndex != -1)) {
            if ((amount <= ((Stock) userAssetList.get(userIndex)).getStockAvailable())) {
                ((Stock) userAssetList.get(userIndex)).minusShareAvailable(amount);
                account.deposit(amount * ((Asset) share).getAssetValue());
                return true;
            }
        }
        return false;
    } // End of method.


    // This method allows the user to buy the Stocks.
    //
    public void buyStocks(Stock share, int amount) {
        int userIndex = getIndex(userAssetList, share.getAssetName());
        if (userIndex == -1) {
            Stock newShare = new Stock(share.getAssetName(), share.getStockSymbol(), share.getAssetValue(), amount);
            userAssetList.add(newShare);
        } else {
            ((Stock) userAssetList.get(userIndex)).plusShareAvailable(amount);
        }
        account.withdraw(amount * share.getAssetValue());
    }  // End of method.


    // This method allows the user to buy the Bonds.
    //
    public void buyBonds(Bond share, int amount) {
        int userIndex = getIndex(userAssetList, share.getAssetName());
        if (userIndex == -1) {
            Bond newShare = new Bond(share.getAssetName(), share.getAssetValue(), amount, share.getInterest());
            userAssetList.add(newShare);
        } else {
            ((Bond) userAssetList.get(userIndex)).plusShareAvailable(amount);
        }
        account.withdraw(amount * share.getAssetValue());
    }  // End of method.


    // This method allows the user to buy the Real Estate.
    //
    public void buyRealEstate(RealEstate name, String place) {
        RealEstate newRealEstate = new RealEstate(place, name.getAssetValue(), name.getArea());
        userAssetList.add(newRealEstate);
        account.withdraw(name.getAssetValue());
    } // End of method.


    // This method allows the user to sell the Real Estate in the portfolio.
    //
    public RealEstate sellRealEstate(String place) {
        int userIndex = getIndex(userAssetList, place);
        if (userIndex != -1) {
            account.deposit(userAssetList.get(userIndex).getAssetValue());
            return (RealEstate) userAssetList.remove(userIndex);
        }
        return null;
    } // End of method.
    

    // This method gives the information of the user's portfolio including the account and securities.
    //
    public String portfolioInfo() {
        String text = "";
        text += "Your balance: " + account.getBalance() + "£" + "\n\n";
        text += "Your asset" + "\n" + "---------------" + "\n";
		for (Asset asset : userAssetList) {
			text += asset.getAssetInfo() + "\n";
		}
        return(text);
    } // End of method.
    

    // This method returns the index of the object in the asset list of the user.
    // 
    public <T> int getIndex(ArrayList<T> array, String share) {
        if (array.size() == 0) {
            return -1;
        }
        for (int i = 0; i < array.size(); i++) {
            if (((Asset) array.get(i)).getAssetName().equals(share)) {
                return i;
            }
        }
        return -1;
    } // End of method
    
}
