package cn.shyshetxwh.Thread.blockingQueueBank;

public class TransferOrder {
    private int from;
    private int to;
    private double amount;

    @Override
    public String toString() {
        return "TransferOrder{" +
                "from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                '}';
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransferOrder(int from, int to, double amount) {

        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public TransferOrder() {

    }
}
