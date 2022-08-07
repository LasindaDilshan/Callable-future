import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main

{


    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    public static void main(String args[]) throws InterruptedException, ExecutionException {

        FactorialCalculator task = new FactorialCalculator(3);

        System.out.println("Submitting Task ...");

        Future future = threadpool.submit(task);
        System.out.println("Task is submitted");


        while (!future.isDone()) {
            System.out.println("Task is not completed yet....");
            Thread.sleep(1); //sleep for 1 millisecond before checking again

            System.out.println("Task is completed, let's check result");


        }
        Object factorial = future.get();
        System.out.println("Factorial of 1000000 is : "  +factorial);

        threadpool.shutdown();

    }
}

class FactorialCalculator implements Callable

{
    private final int number;

    public FactorialCalculator(int number)

    {
        this.number = number;
    }

    @Override
    public Long call()

    {
        long output = 0;
        try {
            output = factorial(number);
        } catch (InterruptedException ex)

        {
            Logger.getLogger(FactorialCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    private long factorial(int number) throws InterruptedException {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be greater than zero");
        }


        long result = 1;
        while (number > 0)

        {
            Thread.sleep(1);
            result = result*number;
            number--;
        }

        return result;
    }
}


