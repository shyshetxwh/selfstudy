package cn.shyshetxwh.chapter06.item38;

/**
 * FileName: ExtendedOperation
 * Author:   admin+shyshetxwh
 * Date:     2021/03/28 22:12
 */
public enum ExtendedOperation implements Operation {
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
