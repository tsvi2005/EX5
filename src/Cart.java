public class Cart {
    private Object user;
    private Product [] products;
    private int [] amount;
    private double cost;

    public Cart(Object user) {
        this.user = user;
        this.products = new Product[0];
        this.cost = 0;
    }
    public Object getUser() {
        return user;
    }

    public Product[] getProducts() {
        return products;
    }

    public double getCost() {
        return cost;
    }

    public void setUser(Object user) {
        this.user = user;
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
    public double costOfCart(){
        double cost=0;
        for (int i=0;i<this.products.length;i++){
            cost=cost+(this.products[i].getPrice()*this.amount[i])*(this.products[i].getDiscount()/100);
        }
        return cost;
    }
}