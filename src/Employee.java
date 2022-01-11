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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}