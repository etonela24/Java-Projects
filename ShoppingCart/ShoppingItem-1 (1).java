/*
@Author: Ornela Amouzou-Adoun
*/


import java.util.Scanner;

/**
 * A class representing a shopping item with a name, quantity, and price.
 */
public class ShoppingItem {

    private String name;
    private int quantity;
    private double price;

    /**
     * Creates a new ShoppingItem object with the given name, quantity, and price.
     *
     * @param name     The name of the shopping item.
     * @param quantity The quantity of the shopping item.
     * @param price    The price per unit of the shopping item.
     */
    public ShoppingItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Creates a new ShoppingItem object by taking input from the user via the console.
     *
     * @param scanner The scanner object to use for taking input.
     * @return A new ShoppingItem object created from user input.
     */
    public static ShoppingItem createItemFromInput(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        return new ShoppingItem(name, quantity, price);
    }

    /**
     * Returns the name of the shopping item.
     *
     * @return The name of the shopping item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity of the shopping item.
     *
     * @return The quantity of the shopping item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the price per unit of the shopping item.
     *
     * @return The price per unit of the shopping item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Calculates and returns the total price of the shopping item.
     *
     * @return The total price of the shopping item.
     */
    public double getTotalPrice() {
        return quantity * price;
    }

    /**
     * Returns a string representation of the ShoppingItem object.
     *
     * @return A string representation of the ShoppingItem object.
     */
    @Override
    public String toString() {
        return name + " x " + quantity + " @ $" + price + " = $" + getTotalPrice();
    }
}
