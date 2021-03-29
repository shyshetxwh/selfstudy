package v17.v17test;

import cn.shyshetxwh.util.CountingGenerator;
import cn.shyshetxwh.util.CountingIntegerList;

import java.util.*;

public class ListPerformance {
    static Random random = new Random();
    static int reps=1000;
    static List<Test<List<Integer>>> tests=new ArrayList<Test<List<Integer>>>();
    static List<Test<LinkedList<Integer>>> qTests=new ArrayList<Test<LinkedList<Integer>>>();

    static {
        tests.add(new Test<List<Integer>>("add") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                int loops=tp.loops;
                int listSize=tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    for (int i1 = 0; i1 < listSize; i1++) {
                        container.add(i1);
                    }
                }
                return loops*listSize;
            }
        });

        tests.add(new Test<List<Integer>>("get") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                int loops=tp.loops*reps;
                int listSize=container.size();
                for (int i = 0; i < loops; i++) {
                    container.get(random.nextInt(listSize));
                }
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("set") {
            int test(List<Integer> container, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = container.size();
                for(int i = 0; i < loops; i++)
                    container.set(random.nextInt(listSize), 47);
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("iteradd") {
            int test(List<Integer> container, TestParam tp) {
                final int LOOPS=1000000;
                int half=container.size()/2;
                ListIterator<Integer> lit = container.listIterator(half);
                for (int i = 0; i < LOOPS; i++) {
                    lit.add(47);
                }
                return LOOPS;
            }
        });

        tests.add(new Test<List<Integer>>("insert") {
            int test(List<Integer> container, TestParam tp) {
                int loops=tp.loops;
                for (int i = 0; i < loops; i++) {
                    container.add(5,47);
                }
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("remove") {
            int test(List<Integer> container, TestParam tp) {
                int loops=tp.loops;
                int size=tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(size));
                    while (container.size() > 5) {
                        container.remove(5);
                    }
                }
                return size*loops;
            }
        });


        qTests.add(new Test<LinkedList<Integer>>("addFirst") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops=tp.loops;
                int size=tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    for (int j = 0; j < size; j++) {
                        container.addFirst(47);
                    }
                }
                return loops*size;
            }
        });

        qTests.add(new Test<LinkedList<Integer>>("addLast") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops=tp.loops;
                int size=tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    for (int j = 0; j < size; j++) {
                        container.addLast(47);
                    }
                }
                return loops*size;
            }
        });

        qTests.add(new Test<LinkedList<Integer>>("rmFirst") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops=tp.loops;
                int size=tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(size));
                    while (container.size()>0){
                        container.removeFirst();
                    }

                }
                return loops*size;
            }
        });

        qTests.add(new Test<LinkedList<Integer>>("rmLast") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops=tp.loops;
                int size=tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(size));
                    while (container.size()>0){
                        container.removeLast();
                    }

                }
                return loops*size;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>>{
        public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
            super(container, tests);
        }

        @Override
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        public static void run(List<Integer> list,List<Test<List<Integer>>> tests){
            new ListTester(list,tests).timedTest();
        }
    }

    public static void main(String[] args) {
        ListTester.run(new ArrayList<Integer>(),tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new Vector<Integer>(), tests);

        Tester.fieldWidth=12;
        Tester<LinkedList<Integer>> qTest=new Tester<LinkedList<Integer>>(new LinkedList<Integer>(),qTests);
        qTest.timedTest();
    }
}
