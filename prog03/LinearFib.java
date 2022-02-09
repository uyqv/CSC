package prog03;

public class LinearFib extends Fib {


    @Override
    public double fib(int n) {
        int a = 0, b = 1;
        int c = 0;
        //
        if (n == 1) {
            return 1;
        }
        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return (double)c;
    }

    @Override
    public double O(int n) {
        return n;
    }
}
