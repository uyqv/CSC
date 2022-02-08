import java.util.InputMismatchException;
import java.util.Scanner;

public class Website {
    private static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        Shop shop = new Shop();
        char option;
        char clotheType;
        int quantity;
        char size;
        int index;

            System.out.println("               ---------- Kazel Clothing ----------");
        do {
            System.out.println("------------------------------------------------------------------------");
            shop.display();
            System.out.println("------------------------------------------------------------------------");
            System.out.print("(B)uy clothe, (R)emove clothe, (E)xit : ");
            option = Character.toUpperCase(keyboard.next().charAt(0));
            switch (option) {
                case 'B':
                    try {
                        System.out.print("(S)weatpants or (H)oodies or Shir(t)s or (P)uffer : ");
                        clotheType = Character.toUpperCase(keyboard.next().charAt(0));

                        System.out.print("Size: (S)mall, (M)edium, (L)arge                  : ");
                        size = Character.toUpperCase(keyboard.next().charAt(0));

                        System.out.print("Quantity                                          : ");
                        quantity = keyboard.nextInt();
                    if (clotheType == 'S') {
                        shop.addClothes(new Sweatpants(size, quantity));
                    } else if (clotheType == 'H') {
                        shop.addClothes(new Hoodie(size, quantity));
                    } else if (clotheType == 'T') {
                        shop.addClothes(new Shirt(size, quantity));
                    } else if (clotheType == 'P') {
                        if (size == 'L') {
                            System.out.println("There are no large sizes");
                        } else {
                            shop.addClothes(new Puffer(size, quantity));
                        }
                    }
                    else {
                        System.out.println("Make sure you entered valid values");
                    }
                    break;
                    } catch (InputMismatchException e) {
                        System.out.println("               ----------- Enter a valid input -----------");
                    }
                case 'R':
                    System.out.print("Index of item to remove             : ");
                    index = keyboard.nextInt();
                    shop.removeClothe(index);
                    break;
            }
        } while (option != 'E');
    }
}
