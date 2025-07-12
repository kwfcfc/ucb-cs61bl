package gh2;

import deque.ArrayDeque61B;
import deque.Deque61B;

/*
 * NOTE: Implementation of GuitarString is OPTIONAL practice, and will not be tested in the autograder.
 * This class will not compile until the Deque61B implementations are complete.
 */

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque61B<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // buffer size may vary during the iteration, so put it in a different variable
        int size = buffer.size();
        for (int i = 0; i < size; i++) {
            buffer.removeFirst();
            Double noise = Math.random() - 0.5;
            buffer.addLast(noise);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        double front = buffer.removeFirst();
        double newFront = buffer.get(0);
        double newEnd = 0.5 * DECAY * (front + newFront);
        buffer.addLast(newEnd);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
