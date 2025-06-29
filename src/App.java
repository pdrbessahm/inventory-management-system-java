import java.util.*;


class Product {

    private static int nextId = 1; //generates the unique IDs automatically

    int id;
    String name;
    int quantity;

    //once we generate the ID automatically, we dont need to declare the int ID anymore
    Product(String name, int quantity) {
        this.id = nextId++;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Quantity: " + quantity;
    }
}

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> inventory = new ArrayList<>();
        int nextId = 1;


        while (true) {
            System.out.println("===== INVENTORY MENU =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Quantity");
            System.out.println("4. Remove Product");
            System.out.println("5. Searching Product by ID");
            System.out.println("6. Show Total Product Count");
            System.out.println("7. Exit");
            System.out.println("----------------------------");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addProduct(inventory, scanner);
                case 2 -> viewProducts(inventory);
                case 3 -> updateQuantity(inventory, scanner);
                case 4 -> System.out.println("You chose 4."); //remove product
                case 5 -> System.out.println("You chose 5."); //searching product by its name
                case 6 -> System.out.println("You chose 6."); //show total product count
                case 7 -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void addProduct(ArrayList<Product> inventory, Scanner scanner) {
        System.out.println("----------------------------");
        System.out.print("Product Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Product Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product newProduct = new Product(name, quantity);
        inventory.add(newProduct);
        System.out.println("----------------------------");
        System.out.println("PRODUCT ADDED SUCCESSFULLY");
        System.out.println("----------------------------");

    }

    public static void viewProducts(ArrayList<Product> inventory) {
        System.out.println("----------------------------");
        System.out.println("ALL INVENTORY PRODUCTS");
        
        if(inventory.isEmpty()) {
            System.out.println("Inventory is Empty.");
            System.out.println("----------------------------");
            return;
        }

        for(int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));

        }
        System.out.println("----------------------------");
    }

    public static void updateQuantity(ArrayList<Product> inventory, Scanner scanner) {
        
        //the same part as we did before to display all inventory items
        System.out.println("----------------------------");
        System.out.println("ALL INVENTORY PRODUCTS");
        System.out.println("----------------------------");

        if(inventory.isEmpty()) {
            System.out.println("Inventory is Empty.");
            System.out.println("----------------------------");
            return;
        }
        //testing the enhanced loop instead of the conventional one
        for(Product p : inventory) {
            System.out.println(p);
        }

        //now we start with the new stuff
        System.out.println("----------------------------");
        System.out.print("Enter the ID of the product you wanna update: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        for(Product p : inventory) {
            if(p.id == idToUpdate) {
                System.out.print("Enter new quantity for '" + p.name + "': ");
                int newQuantity = scanner.nextInt();
                scanner.nextLine();

                p.quantity = newQuantity;
                System.out.println("Quantity updated successfully!");
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("Product with ID " + idToUpdate + " not found.");
        
        }

        System.out.println("----------------------------");
    }
    
    public static int generateNextId(ArrayList<Product> inventory) {
        //had to create this method bc the product ID wasnt changing even when registered more than 1 product

        int maxId = 0;
        for(Product p : inventory) {
            if(p.id > maxId) {
                maxId = p.id;
            }
        }
        return maxId + 1;
    }

    public static void removeProduct(ArrayList<Product> inventory, Scanner scanner) {
        System.out.println("----------------------------");
        System.out.println("REMOVE PRODUCTS");
        System.out.println("----------------------------");

        if(inventory.isEmpty()){
            System.out.println("Inventory is Empty.");
            System.out.println("----------------------------");
            return;
        }

        //once we need to manipulate the elements within the array, we are not going to use the for enhanced loop
        for(int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));
        }

        System.out.print("Enter the product number you want to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        //exception to avoid the user enter an invalid option
        if(index < 1 || index > inventory.size()) {
            System.out.println("Invalid option.");
        } else {
            //creating a variable from type PRODUCT (the class we have created) to storage what .remove has returned to us
            Product removed = inventory.remove(index - 1);
            System.out.println("Product '" + removed.name + "' removed successfully!");
        }

        System.out.println("----------------------------");
    }
}



