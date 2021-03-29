package cn.shyshetxwh.IO.objectStream;

public class Manager extends Employee {
    private Employee secretary;

    public Manager(String n,double s,int y,int m,int d)
    {
        super(n,s,y,m,d);
        this.secretary=null;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return super.toString()+"[secretary="+secretary+"]";
    }
}
