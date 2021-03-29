package cn.shyshetxwh.examples;

import java.util.*;

/**
 * FileName: Animals
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 16:39
 */
public class Animals {
    static Ark ark = new Ark();
    Species species;
    Gender gender;

    public static int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal a : animals) {
            if (candidate == null || !candidate.isPotentialMale(a)) {
                candidate = a;
            } else {
                ark.load(new AnimalPair(candidate, a));
                candidate = null;
                ++numPairs;
            }
        }
        return numPairs;
    }

    static class Animal {
        Species species;
        Gender gender;

        public boolean isPotentialMale(Animal other) {
            return species == other.species && gender != other.gender;
        }

        @Override
        public String toString() {
            return species + "," + gender;
        }
    }

    enum Species {
        AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA,
        IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS,
        PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH,
        UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
    }

    enum Gender {
        MALE, FEMALE
    }

    static class AnimalPair {
        private final Animal one, two;

        public AnimalPair(Animal one, Animal two) {
            this.one = one;
            this.two = two;
        }
    }

    static class SpeciesGenderComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal one, Animal two) {
            int speciesCompare = one.species.compareTo(two.species);
            return (speciesCompare != 0) ? speciesCompare : one.gender.compareTo(two.gender);
        }
    }

    static class Ark {
        private final Set<AnimalPair> loadedAnimals = new HashSet<>();

        public void load(AnimalPair pair) {
            loadedAnimals.add(pair);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Species[] species = Species.values();
            Gender[] genders = Gender.values();
            int s = random.nextInt(species.length);
            int g = random.nextInt(genders.length);
            Animal a = new Animal();
            a.species = species[s];
            a.gender = genders[g];
            animals.add(a);
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int j = loadTheArk(animals);
                    System.out.println(j);
                }
            }).start();
        }

    }

}
