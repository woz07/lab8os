import java.util.Random;

/**
 * Lab 8: Thread Barrier
 *
 * Main class
 *
 */
public class Main {

//    private static int threadCount = 20 ;
//    private static final int barrierSize = 10 ;
//    private static final int sleepTime = 500 ;
    // Moved into main() because can't access

    /**
     * Main program
     *    Create a barrier
     *    Create multiple instances of Process and run them in threads.
     *	 threadCount should be provided as command line argument when you run Main
     * @param args
     */
    public static void main(String[] args) {

        // Create a random source for randomly setting the sleep time of the 
        //  process instances
        int threadCount = 20; // default value
        final int barrierSize = 10;
        final int maxSleepTime = 500;
        Random r = new Random();

        //=
            //Added code
        //=
        if (args.length > 0) {
            threadCount = Integer.parseInt(args[0]);
        }

        if (threadCount < barrierSize) {
            System.out.println("Thread count must be >= barrier size.");
            return;
        }
        //=

        // Print out the number of threads
        System.out.println("Number of threads = " + threadCount);

        // Create the barrier
        Barrier barrier = new Barrier(barrierSize) ;

        // Create and start the process threads
        // There are threadCount threads

        // Add code here
        for (int i = 1; i <= threadCount; i++) {
            int sleepTime = r.nextInt(maxSleepTime);
            Thread t = new Thread(new Process(barrier, i, sleepTime));
            t.start();
        }
    }
}
