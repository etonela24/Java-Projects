import java.util.*;

public class GuitarString {
    private Queue<Double> ringBuffer;
    private int capacity;
    private static final double DECAY_FACTOR = 0.996;

    public GuitarString(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency must be greater than 0");
        }

        capacity = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
        if (capacity < 2) {
            throw new IllegalArgumentException("Ring buffer size must be at least 2");
        }

        ringBuffer = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            ringBuffer.add(0.0);
        }
    }

    public GuitarString(double[] init) {
        if (init.length < 2) {
            throw new IllegalArgumentException("Initial values array must have at least 2 elements");
        }

        capacity = init.length;
        ringBuffer = new LinkedList<>();
        for (double value : init) {
            ringBuffer.add(value);
        }
    }

    public void pluck() {
        Random random = new Random();
        for (int i = 0; i < capacity; i++) {
            double value = random.nextDouble() - 0.5;
            ringBuffer.remove();
            ringBuffer.add(value);
        }
    }

    public void tic() {
        double frontSample = ringBuffer.remove();
        double nextSample = ringBuffer.peek();
        double newSample = DECAY_FACTOR * 0.5 * (frontSample + nextSample);
        ringBuffer.add(newSample);
    }

    public double sample() {
        return ringBuffer.peek();
    }
}
