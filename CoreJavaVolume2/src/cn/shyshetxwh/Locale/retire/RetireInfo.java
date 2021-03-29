package cn.shyshetxwh.Locale.retire;

public class RetireInfo {
    private double savings;//现有存款
    private double contrib;//每年存款
    private double income;//退休后收入
    private int currentAge;//现年龄
    private int retireAge;//退休年龄
    private int deathAge;//死亡年龄
    private double inflationPercent;//通货膨胀率
    private double investPercent;//投资汇报率
    private int age;
    private double balance;

    public double getBalance(int year)
    {
        if (year<currentAge)return 0;
        else if (year==currentAge)
        {
            age=year;
            balance=savings;
            return balance;
        }
        else if (year==age)return balance;
        if (year!=age+1)balance=getBalance(year-1);
        age=year;
        if (age<retireAge)balance+=contrib;
        else balance-=income;
        balance=balance*(1+(investPercent-inflationPercent));
        return balance;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getContrib() {
        return contrib;
    }

    public void setContrib(double contrib) {
        this.contrib = contrib;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public int getRetireAge() {
        return retireAge;
    }

    public void setRetireAge(int retireAge) {
        this.retireAge = retireAge;
    }

    public int getDeathAge() {
        return deathAge;
    }

    public void setDeathAge(int deathAge) {
        this.deathAge = deathAge;
    }

    public double getInflationPercent() {
        return inflationPercent;
    }

    public void setInflationPercent(double inflationPercent) {
        this.inflationPercent = inflationPercent;
    }

    public double getInvestPercent() {
        return investPercent;
    }

    public void setInvestPercent(double investPercent) {
        this.investPercent = investPercent;
    }
}
