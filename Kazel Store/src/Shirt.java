public class Shirt extends Clothes {
    public Shirt(char size, int quantity) {
        super(size, quantity);
    }

    @Override
    public double cost() {
        if (size == 'L')
            return 24.99;
        else if (size == 'M')
            return 19.99;
        else
            return 14.99;
    }
}
