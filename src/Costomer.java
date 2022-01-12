public class Costomer extends User {
    private boolean vip;


    public Costomer(String firstName, String lastName, String userName, String password, boolean vip){
        super(firstName,lastName,userName,password);
        this.vip=vip;
    }

    @Override
    public String toString() {
        return super.toString() + getType();
    }

    @Override
    public String getType() {
        String str = " vip!";
        if(!vip) {
            str = "!";
        }
        return str;
    }

    @Override
    public void setType(Object type) {
        this.vip= (boolean) type;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}