import java.util.ArrayList;

public class Shop {
    private ArrayList<Clothes> allClothes;
    private double totalCost;

    public Shop() {
        allClothes = new ArrayList<>();
    }

    public void addClothes(Clothes clothes) {
        allClothes.add(clothes);
    }

    public void removeClothe(int index) {
        allClothes.remove(index);
    }

    public void display() {
        System.out.println("------------------------------------------------------------------------ \nShopping Cart:");
        for (int i = 0; i < allClothes.size(); i++) {
            System.out.print("(" + i + "): " + allClothes.get(i));
            System.out.printf(" %25s", "Total: $");
            System.out.printf("%5.2f\n", allClothes.get(i).cost()*allClothes.get(i).getQuantity());

            totalCost += (allClothes.get(i).cost()*allClothes.get(i).getQuantity());
        }
        System.out.printf("%63s: $%-5.2f\n", "Total Cost",totalCost);
        totalCost = 0.0;
    }
}
