import java.io.*;
import java.util.ArrayList;
import java.util.*;
//=============================================================================
public class FileAllBoatFleet implements Serializable{
    //-----------------------------------------------------------------------------
    private String name;
    private ArrayList<Boat> allBoats;
    private double totalPaid;
    private double totalSpent;
    //-----------------------------------------------------------------------------
    public FileAllBoatFleet() {

        int index;

        name = null;
        allBoats = new ArrayList<>();
        totalPaid = 0.0;
        totalSpent = 0.0;
    }
    //-----------------------------------------------------------------------------
    public FileAllBoatFleet(String newName) {

        this();
        name = newName;
    }
    //-----------------------------------------------------------------------------
    public void display() {

        int index;

        System.out.println(name + ":");
        for (index = 0; index < allBoats.size(); index++) {
            System.out.println(allBoats.get(index));
        }
        System.out.printf("      Total                          "+
                "                   : Paid $ %9.2f : Spent $ %8.2f\n",totalPaid,totalSpent);
    }
    //-----------------------------------------------------------------------------
    public void addBoat(String boatType, String name, int year, String model, int length, double purchase) {
        //makes a new boat object
        if ((length <= 100) && (purchase <= 1000000)) {
            this.allBoats.add(new Boat(boatType, name, year, model, length, purchase));
            totalPaid += purchase;
        }
        else {
            System.out.println("Length and purchase price can not be more than 100 feet and 1 million dollars.");
        }
    }
    //overriding
    //reads the user boat preference
    public void addBoat(String line) {
        String[] boatInfo = line.split(",");
        this.addBoat(boatInfo[0], boatInfo[1], Integer.parseInt(boatInfo[2]), boatInfo[3], Integer.parseInt(boatInfo[4]), Double.parseDouble(boatInfo[5]));
    }
    //-----------------------------------------------------------------------------
    public void removeBoat(String nameOfBoat) {
        int value = 0;
        boolean valid = false;
        for (int index = 0; index < allBoats.size(); index++) {
            if (allBoats.get(index).getNameOfBoat().equalsIgnoreCase(nameOfBoat)) {
                value = index;
                valid = true;
            }
        }
        //removes cost and expenses from the total
        if (valid) {
            totalPaid = totalPaid - allBoats.get(value).getPurchasePrice();
            totalSpent = totalSpent - allBoats.get(value).getExpenses();
            allBoats.remove(value);
        }
        else {
            System.out.println("Cannot find boat " + nameOfBoat);
        }
    }
    //-----------------------------------------------------------------------------
    public void howMuchSpent(String boatName, double amountSpent) {
        double expense = amountSpent;
        int value = 0;
        double moneyLeft;

        for (int index = 0; index < allBoats.size(); index++) {
            if (allBoats.get(index).getNameOfBoat().equalsIgnoreCase(boatName)) {
                value = index;
            }
        }
        //checks if the user can spend the money
        allBoats.get(value).setExpenses(expense);
        if (allBoats.get(value).getExpenses() <= allBoats.get(value).getPurchasePrice()) {
            System.out.printf("Expense authorized, $%.2f spent.\n", allBoats.get(value).getExpenses());
            totalSpent += expense;
        } else {
            moneyLeft = allBoats.get(value).getPurchasePrice() - (allBoats.get(value).getExpenses() - expense);
            System.out.printf("Expense not permitted, only $%.2f left to spend.\n", moneyLeft);
            allBoats.get(value).subtractExpenses(expense);
        }

    }
    //-----------------------------------------------------------------------------
    //checks if the boat name is in the arraylist
    public boolean ifBoatThere(String boatName) {
        boolean valid = false;
        for (int index = 0; index < allBoats.size(); index++) {
            if (allBoats.get(index).getNameOfBoat().equalsIgnoreCase(boatName)) {
                valid = true;
            }
        }
        return valid;
    }
    //-----------------------------------------------------------------------------
    public static boolean saveBoatFleet(String fileName,
                                        FileAllBoatFleet dealer) {

        ObjectOutputStream toStream = null;

        try {
            toStream = new ObjectOutputStream(new FileOutputStream(fileName));
            toStream.writeObject(dealer);
            return(true);
        } catch (IOException e) {
            System.out.println("ERROR saving " + e.getMessage());
            return(false);
        } finally {
            if (toStream != null) {
                try {
                    toStream.close();
                } catch (IOException e) {
                    System.out.println("ERROR closing " + e.getMessage());
                    return(false);
                }
            }
        }
    }
    public static FileAllBoatFleet loadBoatFleetFromCSV(String fileName) {

        BufferedReader br;
        FileAllBoatFleet boatFleet = new FileAllBoatFleet("Fleet report");
        String line = "";

        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                boatFleet.addBoat(line);
            }
        } catch (IOException e) {
            System.out.println("ERROR loading " + e.getMessage());
            return(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return(null);
        }
        return(boatFleet);
    }
    //-----------------------------------------------------------------------------
    public static FileAllBoatFleet loadBoatFleet(String fileName) {

        ObjectInputStream fromStream = null;
        FileAllBoatFleet local;

        try {
            fromStream = new ObjectInputStream(new FileInputStream(fileName));
            local = (FileAllBoatFleet)fromStream.readObject();
        } catch (IOException e) {
            System.out.println("ERROR loading " + e.getMessage());
            return(null);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return(null);
        } finally {
            if (fromStream != null) {
                try {
                    fromStream.close();
                } catch (IOException e) {
                    System.out.println("ERROR closing " + e.getMessage());
                    return(null);
                }
            }
        }
        return(local);
    }
//-----------------------------------------------------------------------------
}
//=============================================================================