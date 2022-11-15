public class Stock extends Asset {
    String symbol;
    int available;

    // Constructor method.
    //
    public Stock(String name, String symbol, int value, int availabe) {
        this.name = name;
        this.symbol = symbol;
        this.value = value;
        this.available = availabe;
    } // End of method.


    // Constructor method, which compatible to the class Bond.
    //
    public Stock(String name, int value, int availabe) {
        this.name = name;
        this.value = value;
        this.available = availabe;
    } // End of method.


    // This method returns the available shares of the object.
    //
    public int getStockAvailable() {
        return this.available;
    } // End of method.


    // This method returns the symbol of the share.
    //
    public String getStockSymbol() {
        return this.symbol;
    } // End of method.


    // This method reduce the available amount of shares.
    //
    public void minusShareAvailable(int amount) {
        this.available -= amount;
    } // End of method


    // This method increase the available amount of shares.
    public void plusShareAvailable(int amount) {
        this.available += amount;
    } // End of method.


    @Override
    public String getAssetInfo() {
        String text = "";
		text += "Stock name: " + name + "\n";
        text += "Symbol: " + symbol + "\n";
		text += "Price per 1 stock: " + value + "£." + "\n";
		text += "Available: " + available + " unit." + "\n";
		return text;
    }
    
}
