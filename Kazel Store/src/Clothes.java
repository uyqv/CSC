public abstract class Clothes {
    protected char size;
    //private double cost;
    //private String color;
    private int quantity;

    public Clothes(char size, int quantity){
        this.size = size;
        this.quantity =  quantity;
    }

    public String toString() {
        return String.format("%10s Size: '%c', Quantity: %2dx", getClass().getName(), size, quantity);
    }

    public int getQuantity() {
        return  quantity;
    }

    public abstract double cost();
}
