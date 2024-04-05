/*
@Author: Ornela Amouzou-Adoun
*/

import java.util.Scanner;
/**

Represents a shopping list that can store ShoppingItem objects.
*/
public class ShoppingList {
  private ShoppingItem[] items; // An array of ShoppingItem objects
  private int size; // The number of ShoppingItem objects currently in the list

/**

Constructs a new ShoppingList object with an initial capacity of 10.
*/
  public ShoppingList() {
    items = new ShoppingItem[10];
    size = 0;
  }
/**

Adds a ShoppingItem to the shopping list.
@param item the ShoppingItem object to be added to the list
@return true if the item was added successfully, false if the list is full
*/

  public boolean add(ShoppingItem item) {
    if (size < 10) {
      items[size] = item;
      size++;
      return true;
    } else {
      return false;
    }
  }

/**

Calculates the total cost of all ShoppingItem objects in the list.
@return the total cost of all ShoppingItem objects in the list
*/
  public double getTotalCost() {
    double totalCost = 0;
    for (int i = 0; i < size; i++) {
      totalCost += items[i].getTotalPrice();
    }
    return totalCost;
  }

/**

Searches for a ShoppingItem in the list by name.
@param itemName the name of the ShoppingItem to search for
@return the ShoppingItem object with the specified name, or null if not found
*/

  public ShoppingItem searchByName(String itemName) {
    for (int i = 0; i < size; i++) {
      if (items[i].getName().equals(itemName)) {
        return items[i];
      }
    }
    return null;
  }

/**

Returns a string representation of the ShoppingList object.
@return a string representation of the ShoppingList object
*/

  public String toString() {
    if (size == 0) {
      return "No items in your shopping list";
    }
    String output = "ShoppingList has " + size + " shopping items:\n";
    for (int i = 0; i < size; i++) {
      output += items[i].toString() + "\n";
    }
    return output;
  }

/**

Main method for testing the ShoppingList class.
Allows the user to input ShoppingItem objects into the list and perform various operations.
*/

  public static void main(String[] args) {
    ShoppingList shoppingList = new ShoppingList();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Enter item name (or 'done' to exit):");
      String itemName = scanner.nextLine();
      if (itemName.equals("done")) {
        break;
      }
      System.out.println("Enter quantity:");
      int quantity = scanner.nextInt();
      System.out.println("Enter price:");
      double price = scanner.nextDouble();
      scanner.nextLine(); // consume the newline character
      ShoppingItem item = new ShoppingItem(itemName, quantity, price);
      boolean success = shoppingList.add(item);
      if (!success) {
        System.out.println("Shopping list is full");
        break;
      }
    }

    System.out.println(shoppingList);
    System.out.println("Total cost: $" + shoppingList.getTotalCost());

    System.out.println("Enter item name to search for:");
    String itemName = scanner.nextLine();
    ShoppingItem foundItem = shoppingList.searchByName(itemName);
    if (foundItem != null) {
      System.out.println("Found item: " + foundItem);
    } else {
      System.out.println("Item not found");
    }

    scanner.close();
  }
}