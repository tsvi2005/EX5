import java.util.Scanner;

public class Store {
    private Employee[] employees;
    private Costomer[] customers;
    private Product[] products;
    public Scanner scanner=new Scanner(System.in);

    public Store() {
        this.employees = new Employee[0];
        this.customers = new Costomer[0];
        this.products = new Product[0];
    }
    public final String ENTER_YOUR = "Enter your ";
    public final String EMPLOYEE = "employee";
    public final String CUSTOMER = "customer";

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
        final int EMPLOYEES=1;
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
            String rank=employeesRank();
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
            Costomer customer=new Costomer(firstName,lastName,userName,password,vip);
            addCustomerToArray(customer);
        }

    }
    public  String employeesRank(){
        final int REGULAR_USER=1;
        final int MANAGER=2;
        Scanner scanner=new Scanner(System.in);
        String rank;
        int choise;
        System.out.println("What your rank?:(1-regular_user \n2.manager \n3.Management_team ");
        choise= scanner.nextInt();
        if (choise==REGULAR_USER){
            rank="regular_user";
        }
        else if(choise==MANAGER){
            rank="manager";
        }
        else {
            rank="Management_team";
        }
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
        System.out.println("Which user you are? \n 1."+EMPLOYEE+"  \n2."+CUSTOMER);
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
    public void addCustomerToArray(Costomer customer){
        Costomer[] newArray = new Costomer[this.customers.length + 1];
        for (int i = 0; i < this.customers.length; i++) {
            newArray[i] = this.customers[i];
        }
        Costomer propertyToAdd = customer;
        newArray[this.customers.length] = propertyToAdd;
        this.customers = newArray;
    }
    public Object login(){
        Scanner scanner = new Scanner(System.in);
        Object objects=new Object();
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
                objects=employees[indexOfTheUsername(username,userType)];
            }
            else {
                objects=customers[indexOfTheUsername(username,userType)];
            }
        }
        else {
            System.out.println("There isn't user like this");
        }
        return objects;

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
}