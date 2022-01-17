import java.util.Date;

public abstract class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Cart cart;
    private int counter;
    private Date date;

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cart = new Cart();
        this.counter=0;
    }

    public Cart getCart() {
        return cart;
    }

    public String toString() {
        return "Hello " + firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter++;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public abstract boolean isVip();
    public abstract void setVip(boolean vip);

}