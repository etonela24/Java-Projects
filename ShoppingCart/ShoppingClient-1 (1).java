/*
@Author: Ornela Amouzou-Adoun
*/

import java.util.Scanner;

public class ShoppingClient {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ShoppingList shoppingList = new ShoppingList();
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display the menu");
            System.out.println("2. Add a shopping item");
            System.out.println("3. Change the quantity of an item");
            System.out.println("4. Display the shopping list");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nMenu:");
                    System.out.println("1. Display the menu");
                    System.out.println("2. Add a shopping item");
                    System.out.println("3. Change the quantity of an item");
                    System.out.println("4. Display the shopping list");
                    System.out.println("5. Exit");
                    break;
                case 2:
                    System.out.print("\nEnter the name of the item: ");
                    String itemName = input.next();
                    int quantity = 0;
                    while (quantity <= 0) {
                        System.out.print("Enter the quantity of the item: ");
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            input.next();
                        }
                        quantity = input.nextInt();
                        if (quantity <= 0) {
                            System.out.println("Quantity must be greater than 0.");
                        }
                    }
                    double pricePerUnit = 0;
                    while (pricePerUnit <= 0) {
                        System.out.print("Enter the price per unit of the item: ");
                        while (!input.hasNextDouble()) {
                            System.out.println("Invalid input. Please enter a number.");
                            input.next();
                        }
                        pricePerUnit = input.nextDouble();
                        if (pricePerUnit <= 0) {
                            System.out.println("Price per unit must be greater than 0.");
                        }
                    }

                    ShoppingItem item = new ShoppingItem(itemName, quantity, pricePerUnit);
                    if (shoppingList.add(item)) {
                        System.out.println("Item added successfully.");
                    } else {
                        System.out.println("Failed to add item. Shopping list is full.");
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the name of the item: ");
                    input.nextLine(); // consume the newline character
                    String searchName = input.nextLine();
                    ShoppingItem searchItem = shoppingList.searchByName(searchName);
                    if (searchItem == null) {
                        System.out.println("Item not found.");
                    } else {
                        System.out.print("Enter the new quantity of the item: ");
                        int newQuantity = input.nextInt();
                        System.out.println("Quantity changed successfully.");
                    }
                    break;
                case 4:
                    System.out.println();
                    System.out.println(shoppingList.toString());
                    System.out.printf("Total cost: $%.2f\n", shoppingList.getTotalCost());
                    break;
                case 5:
                    System.out.println("\nGoodbye!");
                    break;
               default:
               System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
               break;
               }
               } while (choice != 5);
}
}
