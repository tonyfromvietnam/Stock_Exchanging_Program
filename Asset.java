abstract class Asset implements AssetInterface {

    String name;
    int value;

    // This method returns the name of the asset.
    //
    public String getAssetName() {
        return this.name;
    } // End of method.


    // This method returns the value of the asset.
    //
    public int getAssetValue() {
        return this.value;
    } // End of method.


    // This function is created for polymorphism, which returns the asset information
    // of the chosen asset type.
    //
    public abstract String getAssetInfo();
    
}
