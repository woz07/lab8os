
/**
 * Lab 8: Thread Barrier
 * Barrier Class
 *
 * Processes join the barrier and are held until barrierSize processes have
 * joined.
 *
 *
 */
public class Barrier {

    /**
     * Size of the barrier, which is the minimum number of processes to proceed.
     */
    private int barrierSize;
    private int count;
    private boolean barrierOpen = false;


    /**
     * Create a barrier of a given size
     *
     * @param size
     */
    public Barrier(int size) {
        barrierSize = size;
        System.out.println("Barrier size = " + barrierSize);
    }

    /**
     * Processes join at barrier and either wait or are allowed past.
     *
     * @param p The process joining
     */
    public synchronized void joinBarrier(Process p) {
        System.out.println(p.getName() + " waiting on barrier");

        // add code here
        if (barrierOpen) {
            // if open then pass through
            System.out.println(p.getName() + " passed the barrier");
            return;
        }

        count++;
        if (count > barrierSize) {
            // release barrier once exceed size
            barrierOpen = true;
            notifyAll();
        } else {
            while (!barrierOpen) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(p.getName() + " interrupted");
                }
            }
        }

        System.out.println(p.getName() + " passed the barrier");
    }
}
