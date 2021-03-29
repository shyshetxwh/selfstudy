package cn.shyshetxwh.util;

public class CountingGenerator {
    public static class CountingBoolean implements Generator<Boolean>{
        private boolean value=false;

        @Override
        public Boolean next() {
            value=!value;
            return value;
        }
    }

    public static class CountingByte implements Generator<Byte>{
        private byte value=0;

        @Override
        public Byte next() {
            return value++;
        }
    }

    static char[] chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static class CountingCharacter implements Generator<Character>{
        int index=-1;

        @Override
        public Character next() {
            index=(index+1)%chars.length;
            return chars[index];
        }
    }

    public static class CountingString implements Generator<String>{
        private int length=7;
        Generator<Character> cg=new CountingCharacter();

        public CountingString() {
        }

        public CountingString(int length) {
            this.length = length;
        }

        @Override
        public String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++) {
                buf[i]=cg.next();
            }
            return new String(buf);
        }
    }

    public static class CountingShort implements Generator<Short>{
        private short value=0;

        @Override
        public Short next() {
            return value++;
        }
    }

    public static class CountingInteger implements Generator<Integer>{
        private int value=0;

        @Override
        public Integer next() {
            return value++;
        }
    }

    public static class CountingLong implements Generator<Long>{
        private long value=0;

        @Override
        public Long next() {
            return value++;
        }
    }

    public static class CountingFloat implements Generator<Float>{
        private float value=0;

        @Override
        public Float next() {
            float result=value;
            value+=1.0;
            return result;
        }
    }

    public static class CountingDouble implements Generator<Double>{
        private double value=0;

        @Override
        public Double next() {
            double result=value;
            value+=1.0;
            return result;
        }
    }

}
