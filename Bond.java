// This class is the child class of class Stock.
//

public class Bond extends Stock {
    int interest;

    // Initialise method.
    //
    public Bond(String name, int value, int availabe, int interest) {
        super(name, value, availabe);
        this.interest = interest;
    } // End of method.


    // Method that returns the bond's interest.
    //
    public int getInterest() {
        return this.interest;
    } // End of method.


    // Method that returns the information of the bond.
    //
    public String getAssetInfo() {
        String text = "";
		text += "Bond name: " + name + "\n";
		text += "Price per 1 share: " + value + "£." + "\n";
		text += "Available: " + available + " unit." + "\n";
        text += "Interest: " + interest + "% per year." + "\n";
		return text;
    } // End of method.

}
