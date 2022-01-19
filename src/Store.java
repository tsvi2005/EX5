import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Store {
    private Employee[] employees;
    private Customer[] customers;
    private Product[] products;


    public Store() {
        this.employees = new Employee[0];
        this.customers = new Customer[0];
        this.products = new Product[0];
    }
    public final String ENTER_YOUR = "Enter your ";
    public final String EMPLOYEE = "employee";
    public final String CUSTOMER = "customer";
    public final int EMPLOYEES=1;
    public final int PRINT_CUSTOMER = 1;
    public final int PRINT_VIP_CUSTOMER = 2;
    public final int PRINT_PURCHASE_CUSTOMER = 3;
    public final int MOST_PURCHASE = 4;
    public final int ADD_NEW_PRODUCT = 5;
    public final int CHANGE_INVENTORY=6;
    public final int PURCHASE = 7;
    public final int FIRST_MENU = 8;
    public final int RETURN_FIRST_MENU = -1;

    public void createUser(){
        int userType;
        do {
            userType = whichUserType();
        }while (userType!=1 && userType!= 2);
        createAccount(userType);

    }
    public String firstName(){
        Scanner scanner=new Scanner(System.in);
        String firstName;
        do {
            System.out.println(ENTER_YOUR+"firstName:");
        }while (!onlyLetters(firstName=scanner.next()));
        return firstName;
    }
    public String lastName(){
        Scanner scanner=new Scanner(System.in);
        String lastName;
        do {
            System.out.println(ENTER_YOUR+"lastName:");

        }while (!onlyLetters(lastName=scanner.next()));
        return lastName;
    }
    public void userName(){
        System.out.println(ENTER_YOUR+"userName:");
    }
    public void password(){
        System.out.println(ENTER_YOUR+"password:");
    }
    public void createAccount(int userType){
        Scanner scanner=new Scanner(System.in);
        String firstName = firstName();
        String lastName = lastName();
        String userName;
        do {
            userName();
        }while (isUsernameExist(userName=scanner.next(),userType));
        String password;
        do {
            password();
        }while (!passwordIsProper(password=scanner.next()));
        if(userType==EMPLOYEES){
            int rank=employeesRank();
            Employee employee=new Employee(firstName,lastName,userName,password,rank);
            addEmployeesToArray(employee);
        }
        else {
            String result=vip();
            boolean vip;
            if(result=="yes"){
                vip=true;
            }
            else {
                vip=false;
            }
            Customer customer=new Customer(firstName,lastName,userName,password,vip);
            addCustomerToArray(customer);
        }
    }
    public  int employeesRank(){
        Scanner scanner=new Scanner(System.in);
        int rank;
        do {
            System.out.println("What your rank?:\n1-regular user \n2.manager \n3.Management_team ");
            rank= scanner.nextInt();
        }while (rank!=1 && rank!=2 && rank!=3);
        return rank;
    }
    public  String vip(){
        Scanner scanner=new Scanner(System.in);
        String vipMember;
        do {
            System.out.println("Do you want to be member in vip?:(1.yes\n2.no)");
            vipMember=scanner.next();
        }while (vipMember!="yes" || vipMember!="no");
        return vipMember;
    }

    public  boolean onlyLetters(String str){
        boolean check=false;
        for (int i=0;i<str.length();i++){
            char c1=str.charAt(i);
            if (Character.isLetter(c1)){
                check=true;
            }
            else {
                check=false;
                break;
            }
        }
        return check;
    }

    public  boolean isUsernameExist(String userName,int userType){
        boolean notExists=false;
        if(userType==1) {
            if (this.employees.length==0){
                notExists=false;
            }
            else {
                for (int i = 0; i < this.employees.length; i++) {
                    if (this.employees[i].getUserName().equals(userName)) {
                        notExists = true;
                        break;
                    }
                }
            }
        }
        else {
            if (this.customers.length==0){
                notExists=false;
            }
            else {
                for (int i = 0; i < this.customers.length; i++) {
                    if (this.customers[i].getUserName().equals(userName)) {
                        notExists = false;
                        break;
                    }
                }
            }
        }
        return notExists;
    }
    public int whichUserType(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Which user you are? \n1."+EMPLOYEE+"  \n2."+CUSTOMER);
        int userType = scanner.nextInt();
        return userType;
    }
    public  boolean passwordIsProper(String password){
        boolean passwordCheck=false;
        if (password.length()>=6){
            passwordCheck=true;
        }
        return passwordCheck;
    }
    public  void addEmployeesToArray(Employee employee){
        Employee[] newArray = new Employee[this.employees.length + 1];
        for (int i = 0; i < this.employees.length; i++) {
            newArray[i] = this.employees[i];
        }
        Employee propertyToAdd = employee;
        newArray[this.employees.length] = propertyToAdd;
        this.employees = newArray;
    }
    public void addCustomerToArray(Customer customer){
        Customer[] newArray = new Customer[this.customers.length + 1];
        for (int i = 0; i < this.customers.length; i++) {
            newArray[i] = this.customers[i];
        }
        Customer propertyToAdd = customer;
        newArray[this.customers.length] = propertyToAdd;
        this.customers = newArray;
    }
    public User login(){
        Scanner scanner=new Scanner(System.in);
        User user = null;
        boolean details = false;
        String username = null;
        String password = null;
        int userType = whichUserType();
        userName();
        username=scanner.next();
        password();
        password=scanner.next();
        if (isUsernameExist(username,userType)){
            if (isPasswordCorrect(username,password,userType)){
                details=true;
            }
        }
        if (details){
            if (userType==1){
                user=employees[indexOfTheUsername(username,userType)];

            }
            else {
                user=customers[indexOfTheUsername(username,userType)];

            }
        }
        else {
            System.out.println("There isn't user like this");
        }
        return user;
    }
    public boolean isPasswordCorrect(String username ,String password,int userType){
        boolean passwordCorrect=false;
        if (userType==1) {
            if (isUsernameExist(username, userType)) {
                int index = indexOfTheUsername(username,userType);
                if (employees[index].getPassword().equals(password)) {
                    passwordCorrect = true;
                }
            }
        }
        else {
            if (isUsernameExist(username, userType)) {
                int index = indexOfTheUsername(username,userType);
                if (customers[index].getPassword().equals(password)) {
                    passwordCorrect = true;
                }
            }
        }
        return passwordCorrect;
    }
    public int indexOfTheUsername(String username,int userType){
        int index=-1;
        if (userType==1) {
            for (int i = 0; i < this.employees.length; i++) {
                User currentUser = this.employees[i];
                if (currentUser.getUserName().equals(username)) {
                    index = i;
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < this.customers.length; i++) {
                User currentUser = this.customers[i];
                if (currentUser.getUserName().equals(username)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    public void whichProducts(){
        System.out.println("You must choose the product number you want to buy\n" +
                "If you want to finish the purchase, press -1");
    }
    public void secondMenuCustomer(){
        int num = 1;
        if (products.length==0) {
            System.out.println("There are no products in the store.");
        }else {
            for (int i = 0; i < products.length; i++) {
                System.out.println(num + i + ")");
                products[i].getDescription();
            }
        }
        whichProducts();
    }
    public void secondMenuEmployee(){
        System.out.println("1 - Print a list of all customers.\n" +
                "2 - Print the list of customers who are members of the club only.\n" +
                "3 - Print the list of customers who have made at least one purchase.\n" +
                "4 - Print the customer whose purchase amount is the highest.\n" +
                "5 - Adding a new product to the store\n" +
                "6 - Change inventory status for product\n" +
                "7 - Making a purchase\n" +
                "8 - Logout");
    }
    public void customerList(){
        int num = 1;
        if (this.customers.length==0) {
            System.out.println("There are no customers in the store.");
        }else {
            for (int i = 0; i < this.customers.length; i++) {
                System.out.println(num + i + ") ");
                this.customers[i].toString();
            }
        }
    }

    public void vipCustomerList(){
        int num = 1;
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].isVip()) {
                System.out.println(num + i + ") ");
                customers[i].toString();
            }
        }
        if (num==1){
            System.out.println("There are no vip customers in the store.");
        }

    }
    public void secondMenu(User user){
        Scanner scanner=new Scanner(System.in);
        if (user==null){
            Main.firstMenu();
        }
        else {
            user.toString();
            int userChoise = 0;
            if (user instanceof Employee) {
                do {
                    secondMenuEmployee();
                    userChoise = scanner.nextInt();
                    switch (userChoise) {
                        case PRINT_CUSTOMER:
                            customerList();
                            break;
                        case PRINT_VIP_CUSTOMER:
                            vipCustomerList();
                            break;
                        case PRINT_PURCHASE_CUSTOMER:
                            purchasedCustomer();
                            break;
                        case MOST_PURCHASE:
                            highestPurchases();
                            break;
                        case ADD_NEW_PRODUCT:
                            addProductToStore();
                            break;
                        case CHANGE_INVENTORY:
                            updateInventory();
                            break;
                        case PURCHASE:
                            purchase(user);
                            break;
                    }
                } while (userChoise != FIRST_MENU);
                Main.firstMenu();
            } else {
                do {
                    secondMenuCustomer();
                    userChoise = scanner.nextInt();
                    purchase(user);
                } while (userChoise == -1);
                user.getCart().costOfCart(user);
            }
        }
    }

    public void purchasedCustomer(){
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getCart() != null){
                if (customers[i].getCart().getProducts().length!=0){
                    customers[i].toString();
                }
            }
        }
    }
    public void highestPurchases(){
        int indexHighestPurchase=0;
        if (this.customers.length==0){
            System.out.println("there isn't purchase yet");
        }
        else {
            for (int i = 1; i < customers.length; i++) {
                if (customers[i].getCart() != null && customers[indexHighestPurchase].getCart() != null && customers[i].getCart().costOfCart(customers[i]) > customers[indexHighestPurchase].getCart().costOfCart(customers[indexHighestPurchase])) {
                    indexHighestPurchase = i + 1;
                }
            }
            System.out.println("The customer who made the most purchases: ");
            customers[indexHighestPurchase].toString();
        }
    }

    public void addProductToStore(){
        Scanner scanner=new Scanner(System.in);
        Product[] products1 = new Product[this.products.length+1];
        for (int i=0;i<this.products.length;i++){
            products1[i]=this.products[i];
        }
        String description;
        double price;
        int discount;
        System.out.println("enter the description:");
        description=scanner.next();
        System.out.println("enter the price:");
        price=scanner.nextDouble();
        System.out.println("enter the discount:");
        discount=scanner.nextInt();
        products1[this.products.length]=new Product(description,price,discount);
        this.products=products1;
    }
    public void updateInventory(){
        Scanner scanner=new Scanner(System.in);
        String available;
        int choose;
        for (int i=0;i<this.products.length;i++){
            System.out.println((i+1) + "." + this.products[i]);
        }
        do {
            System.out.println("enter the product to update:");
            choose = scanner.nextInt();
        }while ((choose-1)>this.products.length && (choose-1)<this.products.length);
        do {
            System.out.println("this product is available?:(y or n)");
            available=scanner.next();
            if (available=="y"){
                this.products[choose-1].setAtTheInventory(true);
            }
            else {
                if (available=="n") {
                    this.products[choose - 1].setAtTheInventory(false);
                }
            }
        }while (!available.equals("y") && !available.equals("n"));
    }

    public void purchase(User user) {
        Scanner scanner=new Scanner(System.in);
        Calendar calendar = GregorianCalendar.getInstance();
        Date nowDate = calendar.getTime();
        int choose;
        int amount;
        do {
            for (int i = 0; i < this.products.length; i++) {
                if (this.products[i].isAtTheInventory()) {
                    System.out.print((i + 1) + "." + this.products[i] + ",");
                }
            }
            System.out.println("choose the number of the product you want to add to cart or -1 to finish");
            choose = scanner.nextInt();
            if ((choose - 1) < this.products.length && choose != -1) {
                user.getCart().addProductToCart(this.products[choose - 1]);
                System.out.println("how much you want:");
                amount = scanner.nextInt();
                user.getCart().addAmount(amount);
                user.getCart().printCart();
                System.out.println(user.getCart().getCost());
            }
        } while (choose != -1);
        user.setCounter();
        user.setDate(nowDate);
    }

}