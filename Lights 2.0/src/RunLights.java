import java.util.Scanner;
//=============================================================================
public class RunLights {
    //-----------------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    //-----------------------------------------------------------------------------
    public static void main(String[] args) {

        LightStrip myLights = new LightStrip();
        char option;
        int lumens;
        int index;

        do {
            System.out.println("------------------------------------");
            myLights.display();
            System.out.println("------------------------------------");
            System.out.print("(A)dd light, (R)emove light, e(X)it : ");
            option = Character.toUpperCase(keyboard.next().charAt(0));
            switch (option) {
                case 'A':
                    System.out.print("(F)lourescent or (I)ncandescent : ");
                    option = Character.toUpperCase(keyboard.next().charAt(0));
                    System.out.print("How many lumens                 : ");
                    lumens = keyboard.nextInt();
                    if (option == 'F') {
                        myLights.addLight(new Flourescent(lumens));
                    } else {
                        myLights.addLight(new Incandescent(lumens));
                    }
                    break;
                case 'R':
                    System.out.print("Index of light to remove        : ");
                    index = keyboard.nextInt();
                    myLights.removeLight(index);
                    break;
            }
        } while (option != 'X');

    }
//-----------------------------------------------------------------------------
}