public class Cart {

    private Product [] products;
    private int [] amount;
    private double cost;

    public Cart() {
        this.products = new Product[0];
        this.cost = 0;
    }


    public Product[] getProducts() {
        return products;
    }

    public double getCost() {
        return cost;
    }


    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addProductToCart(Product product){
        Product[] products1=new Product[this.products.length+1];
        for (int i=0;i< this.products.length;i++){
            products1[i]=this.products[i];
        }
        products1[this.products.length]=product;
        this.products=products1;
    }
    public void addAmount(int amount){
        int[] amount1=new int[this.amount.length+1];
        for (int i=0;i<this.amount.length;i++){
            amount1[i] = this.amount[i];
        }
        amount1[this.amount.length]=amount;
        this.amount=amount1;
    }
    public double costOfCart(User user){
        double cost=0;
        if (user instanceof Customer) {
            for (int i = 0; i < this.products.length; i++) {
                if (((Customer) user).isVip()) {
                    cost = cost + (this.products[i].getPrice() * this.amount[i])*(this.products[i].getDiscount()/100);
                }
                else {
                    cost = cost + (this.products[i].getPrice() * this.amount[i]);
                }
            }
        }
        if (user instanceof Employee){
            cost= ((Employee) user).discount(cost);
        }
        return cost;
    }
    public void printCart(){
        for (int i=0;i<this.products.length;i++){
            System.out.println((i+1) + "." + this.products[i] + this.amount[i]);
        }
    }
}