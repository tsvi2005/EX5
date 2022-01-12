public class Employee extends User{
    private String rank;

    public Employee(String firstName, String lastName, String userName, String password,String rank){
        super(firstName,lastName,userName,password);
        this.rank=rank;
    }

    @Override
    public String toString() {
        return super.toString() + " " + rank;
    }

    public String getType() {
        return rank;
    }

    public void setType(Object type) {
        this.rank = (String) type;
    }
}