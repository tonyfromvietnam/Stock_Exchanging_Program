import java.util.ArrayList;
import java.util.Iterator;

public class Agent {

	private ArrayList<Asset> assets;
	private ArrayList<Stock> stocks;
	private ArrayList<Bond> bonds;
	private ArrayList<RealEstate> realEstates;

	// Constructor method.
	//
	public Agent() {
		assets = new ArrayList<>();
		stocks = new ArrayList<>();
		bonds = new ArrayList<>();
		realEstates = new ArrayList<>();
	} // End of method.
	

	// This method returns the asset list of the market.
	//
	public ArrayList<Asset> getAssets() {
		return assets;
    }  // End of method.


	// This method add a stock onto the market.
	// 
    public void addStock(Stock c) {
		stocks.add(c);
		assets.add(c);
    } // End of method.


	// This method add a bond onto the market.
	//
	public void addBond(Bond c) {
		bonds.add(c);
		assets.add(c);
    } // End of method.


	// This method add a real estate onto the market.
	//
	public void addRealEstate(RealEstate c) {
		realEstates.add(c);
		assets.add(c);
    } // End of method.
    

	// This method allows the agent to sell Shares on the market.
	//
    public boolean sellShares(User user, String share, int amount) {
		Iterator<Stock> st = stocks.iterator();
		Iterator<Bond> bo = bonds.iterator();
		while (st.hasNext() || bo.hasNext()) {
			Stock stock = st.next();
			Bond bond = bo.next();
			if (stock.getAssetName().equals(share) || stock.getStockSymbol().equals(share)) {
				boolean result = user.sellShares(stock, amount);
				if (result == true) {
					stock.plusShareAvailable(amount);
					return true;
				}
			} else if (bond.getAssetName().equals(share)) {
				boolean result = user.sellShares(bond, amount);
				if (result == true) {
					bond.plusShareAvailable(amount);
					return true;
				}
			}
		}
		return false;
    } // End of method.


	// This method allows the agent to buy Shares on the market.
	//
	public boolean buyShares(User user, String share, int amount) {
		Iterator<Stock> st = stocks.iterator();
		Iterator<Bond> bo = bonds.iterator();
		while (st.hasNext() || bo.hasNext()) {
			Stock stock = st.next();
			Bond bond = bo.next();
			if (stock.getAssetName().equals(share) || stock.getStockSymbol().equals(share)) {
				if ((amount <= stock.getStockAvailable()) && user.getBalance() >= amount * stock.getAssetValue()) {
					stock.minusShareAvailable(amount);
					user.buyStocks(stock, amount);
					return true;
				}
			} else if (bond.getAssetName().equals(share)) {
				if ((amount <= bond.getStockAvailable()) && user.getBalance() >= amount * bond.getAssetValue()) {
					bond.minusShareAvailable(amount);
					user.buyBonds(bond, amount);
					return true;
				}
			}
		}
		return false;
    } // End of method.


	// This method allows the agent to buy Real Estate on the market.
	//
	public boolean buyRealEstate(User user, String place) {
		Iterator<RealEstate> re = realEstates.iterator();
		while (re.hasNext()) {
			RealEstate realEstate = re.next();
			if (place.equals(realEstate.getAssetName())) {
				if (user.getBalance() >= realEstate.getAssetValue()) {
					user.buyRealEstate(realEstate, place);
					assets.remove(realEstate);
					return true;
				}
			}
		}
		return false;
	} // End of method.


	// This method allows the agent to sell Real Estate on the market.
	public boolean sellRealEstate(User user, String place) {
		RealEstate result = user.sellRealEstate(place);
		if (result != null) {
			realEstates.add(result);
			return true;
		}
		return false;
	} // End of method.
	
}
