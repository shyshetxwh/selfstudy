package v21thread;

import cn.shyshetxwh.util.CountingGenerator;
import cn.shyshetxwh.util.MapData;
import v17.v17test.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract class MapTest extends Tester<Map<Integer,Integer>>{
    public MapTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    class Reader extends TestTask{
        long result=0;

        @Override
        void test() {
            for (int i = 0; i < testCycles; i++) {
                for (int j = 0; j < containerSize; j++) {
                    result+=testContainer.get(j);
                }
            }
        }

        @Override
        void putResults() {
            readResult+=result;
            readTime+=duration;
        }
    }

    class Writer extends TestTask{
        @Override
        void test() {
            for (int i = 0; i < testCycles; i++) {
                for (int j = 0; j < containerSize; j++) {
                    testContainer.put(j,writeData[j]);
                }
            }
        }

        @Override
        void putResults() {
            writeTime+=duration;
        }
    }

    @Override
    void startReadersAndWriters() {
        for (int i = 0; i < nReaders; i++) {
            exec.execute(new Reader());
        }

        for (int i = 0; i < nWriters; i++) {
            exec.execute(new Writer());
        }
    }
}

class SynchronizedHashMapTest extends MapTest{
    public SynchronizedHashMapTest(int nReaders, int nWriters) {
        super("SynchronizedHashMap", nReaders, nWriters);
    }


    @Override
    Map<Integer, Integer> containerInitializer() {
        return Collections.synchronizedMap(new HashMap<Integer, Integer>(MapData.map(
                new CountingGenerator.CountingInteger(),
                new CountingGenerator.CountingInteger(),
                containerSize
        )));
    }
}

class ConcurrentHashMapTest extends MapTest{
    public ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }

    @Override
    Map<Integer, Integer> containerInitializer() {
        return new ConcurrentHashMap<Integer, Integer>(
                MapData.map(
                        new CountingGenerator.CountingInteger(),
                        new CountingGenerator.CountingInteger(),
                        containerSize
                )
        );
    }
}

public class MapComparisons {
    public static void main(String[] args) {
        String[] init={"1","10","10"};
        Tester.initMain(init);
        new SynchronizedHashMapTest(10,0);
        new SynchronizedHashMapTest(9,1);
        new SynchronizedHashMapTest(5,5);

        new ConcurrentHashMapTest(10,0);
        new ConcurrentHashMapTest(9,1);
        new ConcurrentHashMapTest(5,5);

        Tester.exec.shutdown();
    }
}
