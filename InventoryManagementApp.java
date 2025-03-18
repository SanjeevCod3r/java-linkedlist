class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head;

    public Inventory() {
        head = null;
    }

    public void addItem(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
        } else {
            newItem.next = head;
            head = newItem;
        }
    }

    public void removeItem(int itemId) {
        if (head == null) return;

        if (head.itemId == itemId) {
            head = head.next;
            return;
        }

        Item temp = head;
        while (temp != null && temp.next != null) {
            if (temp.next.itemId == itemId) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public void updateItemQuantity(int itemId, int quantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = quantity;
                return;
            }
            temp = temp.next;
        }
    }

    public double calculateInventoryValue() {
        double totalValue = 0;
        Item temp = head;
        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }
        return totalValue;
    }

    public void displayInventory() {
        Item temp = head;
        while (temp != null) {
            System.out.println("Item ID: " + temp.itemId + ", Name: " + temp.itemName + ", Quantity: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }
}

// Example usage
public class InventoryManagementApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem("Laptop", 1, 5, 1000.0);
        inventory.addItem("Phone", 2, 10, 500.0);
        inventory.addItem("Tablet", 3, 7, 300.0);
        inventory.displayInventory();
        inventory.updateItemQuantity(2, 12);
        System.out.println("Total Inventory Value: " + inventory.calculateInventoryValue());
    }
}
