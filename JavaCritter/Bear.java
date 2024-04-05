/*
@Author: Ornela Amouzou-Adoun
*/

import java.awt.*;

/**
 * This class is an object to hold the information and actions for the bear.
 */
public class Bear extends Critter {

    /**
     * Class field: information of the class
     * boolean value for the bear types
     * current status holder
     */
    boolean polar;
    private int status = 0;

    /**
     * Full constructor that gets and stores the boolean value
     *
     * @param polar the value that decides the bear type
     */
    public Bear(boolean polar) {
        this.polar = polar;
    }

    /**
     * Gets the color of the bear depending on the boolean value
     * True for polar bear (White)
     * False for black bear (Black)
     *
     * @return the color of the bear
     */
    public Color getColor() {
        if (polar) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * Returns a string representation of the Bear object
     * Changes the sign between each step
     *
     * @return "/" for status = 0, "\" for status = 1, "ERROR" for any other value of status
     */
    @Override
    public String toString() {
        if (status == 0) {
            return "/";
        } else if (status == 1) {
            return "\\";
        } else {
            return "ERROR";
        }
    }

    /**
     * Takes the CritterInfo class and decides which action to return
     *
     * @param info CritterInfo object that contains information about the bear's surrounding environment
     * @return the Action constant based on the surrounding condition
     * Infect if other kind of critter is in the front
     * Hop if nothing is in the front
     * Turn left for everything else
     */
    public Action getMove(CritterInfo info) {
        if (status == 0) {
            status = 1;
        } else if (status == 1) {
            status = 0;
        }

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }
}