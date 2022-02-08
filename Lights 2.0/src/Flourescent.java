public class Flourescent extends Light{
    public Flourescent(int lumens) {
        super(lumens);
    }

    @Override
    public double heatOutput() {
        return 0;
    }

    @Override
    public boolean isFlourescent() {
        return true;
    }
}
