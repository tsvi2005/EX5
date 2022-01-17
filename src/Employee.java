public class Employee extends Customer{
    private int rank;
    public final int REGULAR_WORKER = 1;
    public final int DIRECTER = 2 ;
    public final int MANAGEMENT_TEAM = 3;

    public Employee(String firstName, String lastName, String userName, String password,int rank){
        super(firstName,lastName,userName,password,false);
        this.rank=rank;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str = str.substring(0,str.length()-1);
        switch (this.rank) {
            case REGULAR_WORKER:
                str = str + " regular worker!";
                break;
            case DIRECTER:
                str = str + " director!";
                break;
            case MANAGEMENT_TEAM:
                str = str + " member of the management team";
                break;
        }
        return str;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank =rank;
    }
    public double discount(double payment){
        double afterDiscount;
        afterDiscount = payment*(10-getRank()/10);
        return afterDiscount;
    }
}