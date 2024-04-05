import java.util.HashMap;
import java.util.Map;

/**
 * The Guitar37 class represents a 37-string guitar.
 * Each string is associated with a specific key on the keyboard.
 * It implements the Guitar interface.
 */
public class Guitar37 implements Guitar {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private GuitarString[] strings;
    private int time;
    private Map<Character, Integer> keyToPitch;

    /**
     * Constructs a new Guitar37 object with 37 strings.
     * Initializes the strings array and key-to-pitch map.
     * Creates GuitarString objects for each string with corresponding frequencies.
     */
    public Guitar37() {
        strings = new GuitarString[KEYBOARD.length()];
        keyToPitch = new HashMap<>();

        for (int i = 0; i < strings.length; i++) {
            double frequency = 440.0 * Math.pow(2.0, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);

            char key = KEYBOARD.charAt(i);
            keyToPitch.put(key, i - 24);
        }
    }

    /**
     * Plays the note with the specified pitch.
     * If the pitch is within the valid range, the corresponding string is plucked.
     * Otherwise, throws an IllegalArgumentException.
     *
     * @param pitch the pitch of the note to be played
     * @throws IllegalArgumentException if the pitch is out of range
     */
    public void playNote(int pitch) {
        int index = pitch + 24;
        if (index >= 0 && index < strings.length) {
            strings[index].pluck();
        } else {
            throw new IllegalArgumentException("Invalid pitch: " + pitch);
        }
    }

    /**
     * Checks if a string is associated with the specified key.
     *
     * @param key the key to check
     * @return true if the key has a corresponding string, false otherwise
     */
    public boolean hasString(char key) {
        return keyToPitch.containsKey(key);
    }

    /**
     * Plucks the string associated with the specified key.
     * If the key is not valid, throws an IllegalArgumentException.
     *
     * @param key the key associated with the string to pluck
     * @throws IllegalArgumentException if the key is invalid
     */
    public void pluck(char key) {
        if (!hasString(key)) {
            throw new IllegalArgumentException("Invalid key: " + key);
        }
        int pitch = keyToPitch.get(key);
        playNote(pitch);
    }

    /**
     * Returns the combined sample value of all the guitar strings.
     *
     * @return the sum of samples from all the strings
     */
    public double sample() {
        double sum = 0.0;
        for (GuitarString string : strings) {
            sum += string.sample();
        }
        return sum;
    }

    /**
     * Advances the time by one tic for all the guitar strings.
     */
    public void tic() {
        time++;
        for (GuitarString string : strings) {
            string.tic();
        }
    }

    /**
     * Returns the current time.
     *
     * @return the current time value
     */
    public int time() {
        return time;
    }
}
