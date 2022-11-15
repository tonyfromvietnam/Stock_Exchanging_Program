public class RealEstate extends Asset {
    private int area;

    // Constructor method.
    //
    public RealEstate(String name, int value, int area) {
        this.name = name;
        this.value = value;
        this.area = area;
    } // End of method.


    // This method returns the area of the real estate.
    //
    public int getArea() {
        return this.area;
    } // End of method.

    
    @Override
    public String getAssetInfo() {
        String text = "";
		text += "Real Estate Name: " + name + "\n";
		text += "Price: " + value + "£." + "\n";
		text += "Area: " + area + " m." + "\n";
		return text;
    }

}
