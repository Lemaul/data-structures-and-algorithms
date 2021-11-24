package classes.class02;

public class Main {



    public static void main(String[] args) {

        FibonacciGenerator<Integer> fib = new FibonacciGenerator<>(5);

        fib.generate();
        System.out.println(fib);
    }
}
