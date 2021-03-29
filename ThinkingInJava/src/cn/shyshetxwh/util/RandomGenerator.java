package cn.shyshetxwh.util;

import java.util.Random;

public class RandomGenerator {
    private static Random r=new Random(47);

    public static class RandomBoolean implements Generator<Boolean>{
        @Override
        public Boolean next() {
            return r.nextBoolean();
        }
    }

    public static class RandomByte implements Generator<Byte>{
        @Override
        public Byte next() {
            return (byte)r.nextInt();
        }
    }

    public static class RandomCharacter implements Generator<Character>{
        @Override
        public Character next() {
            return CountingGenerator.chars[r.nextInt(CountingGenerator.chars.length)];
        }
    }

    public static class RandomString extends CountingGenerator.CountingString{
        {cg=new RandomCharacter();}
        public RandomString() {}

        public RandomString(int length) {
            super(length);
        }
    }

    public static class RandomShort implements Generator<Short>{
        @Override
        public Short next() {
            return (short)r.nextInt();
        }
    }

    public static class RandomInteger implements Generator<Integer>{
        private int mod=10000;

        public RandomInteger(int mod) {
            this.mod = mod;
        }

        public RandomInteger() {

        }

        @Override
        public Integer next() {
            return r.nextInt(mod);
        }
    }

    public static class RandomLong implements Generator<Long>{
        private int mod=10000;

        public RandomLong(int mod) {
            this.mod = mod;
        }

        public RandomLong() {

        }

        @Override
        public Long next() {
            return new Long(r.nextInt(mod));
        }
    }

    public static class RandomFloat implements Generator<Float>{
        @Override
        public Float next() {
            int trimmed=Math.round(r.nextFloat()*100);
            return ((float)trimmed)/100;
        }
    }

    public static class RandomDouble implements Generator<Double>{
        @Override
        public Double next() {
            long trimmed=Math.round(r.nextDouble()*100);
            return ((double)trimmed)/100;
        }
    }


}
