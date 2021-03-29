package cn.shyshetxwh.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class DownstreamCollectors {

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).map(l->l.split(", ")).map(a->new City(a[0],a[1],Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(Locale::getCountry, toSet()));
        System.out.println("countryToLocaleSet = " + countryToLocaleSet);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocaleCounts = " + countryToLocaleCounts);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(groupingBy(Locale::getDisplayCountry, mapping(Locale::getDisplayLanguage, toSet())));
        System.out.println("countryToLanguages = " + countryToLanguages);

        Stream<City> cities = readCities("./CoreJavaVolume2/resource/cities.txt");
        Map<String, Integer> stateToAllPopulations = cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));
        System.out.println("stateToAllPopulations = " + stateToAllPopulations);

        cities = readCities("./CoreJavaVolume2/resource/cities.txt");
        Map<String, Optional<String>> StateToLonggestCityName = cities.collect(groupingBy(City::getState, mapping(City::getName, maxBy(Comparator.comparing(String::length)))));
        System.out.println("StateToLonggestCityName = " + StateToLonggestCityName);

        cities = readCities("./CoreJavaVolume2/resource/cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(groupingBy(City::getState, summarizingInt(City::getPopulation)));
        System.out.println(stateToCityPopulationSummary.get("NY"));

        cities = readCities("./CoreJavaVolume2/resource/cities.txt");
        Map<String, String> stateToCityNames = cities.collect(groupingBy(City::getState, reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)));
        System.out.println("stateToCityNames = " + stateToCityNames);

        cities = readCities("./CoreJavaVolume2/resource/cities.txt");
        stateToCityNames = cities.collect(groupingBy(City::getState, mapping(City::getName, joining(","))));
        System.out.println("stateToCityNames = " + stateToCityNames);


    }

}
