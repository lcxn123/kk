package gh2;
import deque.ArrayDeque61B;
import deque.Deque61B;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private Deque61B<Double> buffer;
     private int count;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int)Math.round(SR / frequency);
        count = capacity;
        buffer = new ArrayDeque61B<>(capacity,0.0);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        for (int i = 0; i < count; i++) {
            double r = Math.random() - 0.5;
            buffer.addFirst(r);
        }


    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double valueOne = buffer.removeFirst();
        double valueTwo = buffer.get(0);
        buffer.addLast(((valueOne + valueTwo) / 2) * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return this.buffer.get(0);
    }
}

