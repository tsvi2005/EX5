public class Product {
    private String description;
    private double price;
    private double discount;
    private boolean atTheInventory;

    public Product(String description, double price, double discount) {
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.atTheInventory=true;
    }

    @Override
    public String toString() {
        return  description + price + ", atTheInventory=" + atTheInventory;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public boolean isAtTheInventory() {
        return atTheInventory;
    }

    public void setAtTheInventory(boolean atTheInventory) {
        this.atTheInventory = atTheInventory;
    }
}