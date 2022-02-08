public class Sweatpants extends Clothes{
    public Sweatpants(char size, int quantity) {
        super(size, quantity);
    }

    @Override
    public double cost() {
        if (size == 'L')
            return 49.99;
        else if (size == 'M')
            return 39.99;
        else
            return 29.99;
    }
}
