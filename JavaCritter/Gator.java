/*
@Author: Ornela Amouzou-Adoun
*/

import java.awt.*;
import java.util.*;

/**
 * This class is an object to hold the information and actions for the gator.
 */
public class Gator extends Critter {
    /**
     * Class field: information of the class current status holder
     */
    private int status = 0;
    private Random rand = new Random();

    /**
     * Constructor
     */
    public Gator() {
    }

    /**
     * Getter to show the color of the gator Looks like shining
     * 
     * @return the color of the gator
     */
    public Color getColor() {
        if (status == 0) {
            return Color.YELLOW;
        } else {
            return Color.BLACK;
        }
    }

    @Override
    public String toString() {
        return "Gator";
    }

    /**
     * Takes the CritterInfo class and decides which action to return. Updates the
     * status so it can change the color.
     * 
     * @return the Action constant based on the surrounding condition Infect if
     *         other kind of critter is in the front Turn left if facing the wall
     *         and having same critter facing same way next to Turn right if facing
     *         the wall and having same critter facing same way next to Hop if
     *         possible Turn right for everything else In order to try winning
     *         everytime I was thinking to make the gator to gather up whenever
     *         they meet each other. And stay as a group all facing opposite from
     *         each other so it can infect the animal whenever other species touch
     *         them. But I couldn't find how to make them stay as a group.
     */
    public Action getMove(CritterInfo info) {
        int r = rand.nextInt(3);
        if (status == 0) {
            status = 1;
        } else if (status == 1) {
            status = 0;
        }

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL) {
            if (info.getRight() == Neighbor.SAME && info.getRightDirection() == info.getDirection()) {
                return Action.RIGHT;
            } else if (info.getLeft() == Neighbor.SAME && info.getLeftDirection() == info.getDirection()) {
                return Action.LEFT;
            } else {
                return null;
            }
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.LEFT;
        } else {
            return Action.LEFT;
        }
    }
}
