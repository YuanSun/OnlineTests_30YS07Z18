package com.acquisio.basic.java.question04;

/**
 * QUESTION 5: Generics
 * Here is a design of a class that acts as an inventory of items:
 * Vegetable and Fruit.
 * <p>
 * Provide a version of the Inventory class that uses generics.
 * Feel free to add any additional fields.
 * Make sure that the method stockInventory() is working properly
 * by removing all class casts.
 *
 * IMPORTANT: Add all missing javadoc and/or unit tests that you think is necessary.
 */
public class Generics {
    public static void main(String[] args) {
        Generics instance = new Generics();
        instance.stockInventory();
    }

    private void stockInventory() {
        // TODO: The following code should work with generics. No casting should be necessary.
        Inventory<Fruit> fruitInventory = getFruitInventory();
        Fruit apple = fruitInventory.findFirst("Apple");
        System.out.println(String.format("Apple [%s] : %d units", apple.getColor(), apple.getQuantity()));
        Inventory<Vegetable> vegetableInventory = getVegetableInventory();
        Vegetable salad = vegetableInventory.findFirst("Salad");
        System.out.println(String.format("Salad [%s] : %d units", salad.getType(), salad.getQuantity()));
    }

    private Inventory<Vegetable> getVegetableInventory() {
        Inventory<Vegetable> vegetableInventory = new Inventory<>();
        vegetableInventory.add(new Vegetable("Salad", "Iceberg", "Québec", 19, 1.39d));
        vegetableInventory.add(new Vegetable("Salad", "Boston", "Québec", 18, 1.39d));
        vegetableInventory.add(new Vegetable("Salad", "Iceberg", "USA", 17, 1.39d));
        return vegetableInventory;
    }

    private Inventory<Fruit> getFruitInventory() {
        Inventory<Fruit> fruitInventory = new Inventory<>();
        fruitInventory.add(new Fruit("Apple", "red", 50, 0.99d));
        fruitInventory.add(new Fruit("Apple", "green", 45, 1.29d));
        fruitInventory.add(new Fruit("Banana", "yellow", 150, 0.49d));
        fruitInventory.add(new Fruit("Pineapple", "yellow", 10, 8.99d));
        return fruitInventory;
    }
}
