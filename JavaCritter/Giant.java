/*
@Author: Ornela Amouzou-Adoun
*/

import java.awt.*;
import java.util.Scanner;

/**
 * This class is an object to hold the information
 * and actions for the giant.
 */
public class Giant extends Critter {

    /**
     * Class field: information of the class
     * count that hold the current step number
     */
    private int count = 0;
    private Scanner scanner;

    public Giant() {
        scanner = new Scanner(System.in);
    }

    /**
     * getter
     * show the color gray of the giant
     *
     * @return the color of the giant
     */
    public Color getColor() {
        return Color.GRAY;
    }

    @Override
    // Change the name every six steps for 5 rounds and repeat
    public String toString() {
        int change = count / 6;

        if (change == 0) {
            return "fee";
        } else if (change == 1) {
            return "fie";
        } else if (change == 2) {
            return "foe";
        } else if (change == 3) {
            return "fum";
        } else {
            return "ERROR";
        }
    }

    /**
     * take the CritterInfo class and decide which action to return
     * update the count for the steps, set the step to 0 to control the
     * change in names
     *
     * @return the Action constant based on the surrounding condition
     * Infect if other kind of critter is in the front
     * Hop if nothing is in the front
     * Turn right for everything else
     */
    public Action getMove(CritterInfo info) {
        if (count > 22) {
            count = 0;
        }

        count++;

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }
}