package v14RTTI;

import v14RTTI.pets.LiteralPetCreator;
import v14RTTI.pets.Pet;
import v14RTTI.pets.Pets;

import java.util.LinkedHashMap;
import java.util.Map;

public class PetCount3 {
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>,Integer>
    {
        private static Map<Class<? extends Pet>,Integer> map=new LinkedHashMap<>();
        static {
            for (Class<? extends Pet> type : LiteralPetCreator.allTypes) {
                map.put(type,0);
            }
        }
        public PetCounter() {
            super(map);

        }

        public void count(Pet pet)
        {
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                if (pair.getKey().isInstance(pet))
                    put(pair.getKey(),pair.getValue()+1);
            }
        }

        public String toString()
        {
            StringBuilder result = new StringBuilder("{");
            for(Map.Entry<Class<? extends Pet>,Integer> pair
                    : entrySet()) {
                result.append(pair.getKey().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(", ");
            }
            result.delete(result.length()-2, result.length());
            result.append("}");
            return result.toString();
        }
    }

    public static void main(String[] args) {
        PetCounter counter = new PetCounter();
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName()+" ");
            counter.count(pet);
        }
        System.out.println();
        System.out.println(counter);
    }
}
