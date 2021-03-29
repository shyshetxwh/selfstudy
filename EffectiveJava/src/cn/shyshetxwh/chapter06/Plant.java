package cn.shyshetxwh.chapter06;

import java.util.*;
import java.util.stream.Collectors;

/**
 * FileName: Plant
 * Author:   admin+shyshetxwh
 * Date:     2021/03/25 23:30
 */
public class Plant {
    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {
                new Plant("Basil", LifeCycle.ANNUAL),
                new Plant("Carroway", LifeCycle.BIENNIAL),
                new Plant("Dill", LifeCycle.ANNUAL),
                new Plant("Lavendar", LifeCycle.PERENNIAL),
                new Plant("Parsley", LifeCycle.BIENNIAL),
                new Plant("Rosemary", LifeCycle.PERENNIAL)
        };

        //第一种方式
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<LifeCycle, Set<Plant>>(LifeCycle.class);
        for (LifeCycle lifeCycle : LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
        }
        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }
        System.out.println(plantsByLifeCycle);

        System.out.println(Arrays.stream(garden).collect(Collectors.groupingBy(p -> p.lifeCycle)));

        System.out.println(Arrays.stream(garden).collect(Collectors.groupingBy(p -> p.lifeCycle,
                () -> new EnumMap<>(LifeCycle.class), Collectors.toSet())));

    }
}
