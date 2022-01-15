import java.util.Scanner;

public class Store {
    private Employee[] employees;
    private Customer[] customers;
    private Product[] products;
    public Scanner scanner=new Scanner(System.in);

    public Store() {
        this.employees = new Employee[0];
        this.customers = new Customer[0];
        this.products = new Product[0];
    }
    public final String ENTER_YOUR = "Enter your ";
    public final String EMPLOYEE = "employee";
    public final String CUSTOMER = "customer";
    public final int EMPLOYEES=1;

    public void createUser(){
        Scanner scanner=new Scanner(System.in);
        int userType;
        do {
            userType = whichUserType();
            createAccount(userType);
        }while (userType<0 ||userType>2);

    }
    public String firstName(){
        String firstName;
        do {
            System.out.println(ENTER_YOUR+"firstName:");
        }while (!onlyLetters(firstName=scanner.next()));
        return firstName;
    }
    public String lastName(){
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
        }while (rank>0 && rank<4);
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
        System.out.println("Which user you are? \n1."+EMPLOYEE+"  \n2."+CUSTOMER);
        int userType=scanner.nextInt();
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
        Scanner scanner = new Scanner(System.in);
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
        int productNumber=0;
        while (productNumber==-1);{
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
             productNumber = scanner.nextInt();
            System.out.println("How many items do you want from this product");
        }
    }
    public void secondMenuEmployee(){
        customerList();
        vipCustomerList();

    }
    public void customerList(){
        int num = 1;
        if (customers.length==0) {
            System.out.println("There are no costomers in the store.");
        }else {
            for (int i = 0; i < customers.length; i++) {
                System.out.println(num + i + ")");
                customers[i].toString();
            }
        }
    }

    public void vipCustomerList(){
        int num = 1;
            for (int i = 0; i < customers.length; i++) {
                if (customers[i].isVip()) {
                    System.out.println(num + i + ")");
                    customers[i].toString();
                }
            }
            if (num==1){
                    System.out.println("There are no vip costomers in the store.");
            }

    }
    public void secondMenu(int userType){
        if (userType==EMPLOYEES){
            secondMenuEmployee();
        }else {
            secondMenuCustomer();
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

        for (int i = 1; i < customers.length; i++) {
            if (customers[i].getCart() != null && customers[indexHighestPurchase].getCart() != null && customers[i].getCart().costOfCart(customers[i]) > customers[indexHighestPurchase].getCart().costOfCart(customers[indexHighestPurchase])){
               indexHighestPurchase=i+1;
            }
        }
        System.out.println("The customer who made the most purchases: ");
        customers[indexHighestPurchase].toString();
    }
    public void purchase(User user) {
        Scanner scanner = new Scanner(System.in);
        int choose;
        int amount;
        do {
            for (int i = 0; i < this.products.length; i++) {
                System.out.print((i + 1) + "." + this.products[i] + ",");
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
    }
}