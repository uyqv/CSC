import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
//=============================================================================
public class FileAllBoats {
    //-----------------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    private static final String dbFileName = "FleetData.db";
    //-----------------------------------------------------------------------------
    public static void main(String[] args) {

        FileAllBoatFleet boats;
        char option;

        //Checks the configuration
        if (args.length > 0) {
            // load from csv file
            //src/FleetData.csv
            String fileName = args[0];
            if ((boats = FileAllBoatFleet.loadBoatFleetFromCSV(fileName)) == null) {
                return;
            }
        } else {
            //.db file
            boats = FileAllBoatFleet.loadBoatFleet(dbFileName);
        }
        //menu
        do {
            System.out.print("\n(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            String nextLine = keyboard.nextLine();
            option = Character.toUpperCase(nextLine.charAt(0));
            switch (option) {
                case 'R':
                    //remove
                    System.out.print("Which boat do you want to remove?           : ");
                    String removeBoat = keyboard.nextLine();
                    boats.removeBoat(removeBoat);
                    break;
                case 'E':
                    //expense
                    System.out.print("Which boat do you want to spend on?         : ");
                    String boatSpentOn = keyboard.nextLine();
                    if (boats.ifBoatThere(boatSpentOn)){
                        System.out.print("How much do you want to spend?              : ");
                        try {
                            double expense = keyboard.nextDouble();
                            boats.howMuchSpent(boatSpentOn, expense);
                        } catch (InputMismatchException e) {
                            System.out.println("Enter information correctly");
                        }
                        //boats.howMuchSpent(boatSpentOn, expense);
                        keyboard.nextLine();
                    }
                    else {
                        System.out.println("Cannot find boat " + boatSpentOn);
                    }
                    break;
                case 'P':
                    //print
                    boats.display();
                    break;
                case 'A':
                    //add
                    System.out.print("Please enter the new boat CSV data          : ");
                    String boatInfo = keyboard.nextLine();
                    try {
                        boats.addBoat(boatInfo);
                    } catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Enter information correctly");
                    }
                    break;
                case 'X':
                    //exit
                    System.out.println("\nExiting the Fleet Management System");
                    break;
                default:
                    System.out.println("Invalid menu option, try again");
                    break;
            }
        } while (option != 'X');

        FileAllBoatFleet.saveBoatFleet(dbFileName, boats);
    }
//-----------------------------------------------------------------------------
}
//=============================================================================
