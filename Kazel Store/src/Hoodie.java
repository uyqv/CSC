public class Hoodie extends Clothes{
    public Hoodie(char size, int quantity) {
        super(size, quantity);
    }

    @Override
    public double cost() {
        if (size == 'L')
            return 69.99;
        else if (size == 'M')
            return 59.99;
        else
            return 49.99;
    }
}
