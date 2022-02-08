public class Puffer extends Clothes {
    public Puffer(char size, int quantity) {
        super(size, quantity);
    }

    @Override
    public double cost() {
        if (size == 'M')
            return 79.99;
        else
            return 69.99;
    }
}
