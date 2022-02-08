import java.io.*;
//=============================================================================
public class Boat implements Serializable {
    //-----------------------------------------------------------------------------
    private TypeOfBoat type;
    private String nameOfBoat;
    private int yearOfManufacture;
    private String model;
    private int length;
    private double purchasePrice;
    private double expenses;
    //-----------------------------------------------------------------------------
    public Boat() {
        type = null;
        nameOfBoat = "";
        yearOfManufacture = 0;
        model = "";
        length = 0;
        purchasePrice = 0.00;
        expenses = 0.00;
    }
    //-----------------------------------------------------------------------------
    public Boat(String type, String nameOfBoat, int yearOfManufacture, String model, int length, double purchasePrice) {

        this();
        if (type.equalsIgnoreCase("SAILING")) {
            this.type = TypeOfBoat.SAILING;
        }
        if (type.equalsIgnoreCase("POWER")) {
            this.type = TypeOfBoat.POWER;
        }
        this.nameOfBoat = nameOfBoat;
        this.yearOfManufacture = yearOfManufacture;
        this.model = model;
        this.length = length;
        this.purchasePrice = purchasePrice;
    }
    //-----------------------------------------------------------------------------
    public String toString() {

        return(String.format("      %-7s %-15s %5d %-10s %7d' : Paid $ %9.2f :" +
                " Spent $ %8.2f", type, nameOfBoat,yearOfManufacture, model, length, purchasePrice,expenses));
    }
    //-----------------------------------------------------------------------------
    public double getPurchasePrice() { return(purchasePrice); }

    public String getNameOfBoat() { return nameOfBoat; }

    public double getExpenses() { return expenses; }

    public void setExpenses(double expense) { expenses += expense; }

    public void subtractExpenses(double expense) { expenses -= expense; }

    //-----------------------------------------------------------------------------
}
//=============================================================================