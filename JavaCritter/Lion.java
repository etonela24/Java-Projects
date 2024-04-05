/*
@Author: Ornela Amouzou-Adoun
*/

import java.awt.*;
import java.util.*;

/**
 * This class is an object to hold the information and actions for the Lion.
 */
public class Lion extends Critter {
    /*
     * Class field: information of the class a random number to make decision that altered every time
     * a value that hold the previous random number count that hold the current steps
     */
    Random rand = new Random();
    private int colorIndex = rand.nextInt(3); // Randomly pick the first color index
    private int stepCount = 0;

    /**
     * Constructor
     */
    public Lion() {
    }

    /**
     * Randomly picks one of three colors (Color.RED, Color.GREEN, Color.BLUE) and uses that color for
     * three moves, then randomly picks one of those colors again for the next three moves, then randomly
     * picks another one of those colors for the next three moves, and so on.
     * @return the color of the Lion
     */
    public Color getColor() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
        return colors[colorIndex];
    }

    @Override
    public String toString() {
        return "L";
    }

    /**
     * Always infect if an enemy is in front, otherwise if a wall is in front or to the right, then turn
     * left, otherwise if a fellow Lion is in front, then turn right, otherwise hop.
     *
     * Update the color index every three steps to change the color of the Lion.
     *
     * @return the Action constant based on the surrounding condition
     */
    public Action getMove(CritterInfo info) {
        stepCount++;
        if (stepCount > 2) {
            // Randomly choose a new color index different from the previous one
            int newColorIndex = rand.nextInt(3);
            while (newColorIndex == colorIndex) {
                newColorIndex = rand.nextInt(3);
            }
            colorIndex = newColorIndex;
            stepCount = 0;
        }

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return null;
        }
    }
}
