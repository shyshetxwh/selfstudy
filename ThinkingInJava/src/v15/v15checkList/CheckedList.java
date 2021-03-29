package v15.v15checkList;

import v14RTTI.pets.Cat;
import v14RTTI.pets.Dog;
import v14RTTI.pets.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckedList {

    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs)
    {
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dogs1=new ArrayList<Dog>();
        oldStyleMethod(dogs1);

        List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
        try {
            oldStyleMethod(dogs2);
        } catch (Exception e) {
            System.out.println(e);
        }

        List<Pet> pets = Collections.checkedList(new ArrayList<Pet>(), Pet.class);
        pets.add(new Cat());
        pets.add(new Dog());
    }
}
