package v14RTTI.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types=new ArrayList<>();
    private static String[] typeNames={
            "v14RTTI.pets.Mutt",
            "v14RTTI.pets.Pug",
            "v14RTTI.pets.EgyptianMau",
            "v14RTTI.pets.Cymric",
            "v14RTTI.pets.Rat",
            "v14RTTI.pets.Mouse",
            "v14RTTI.pets.Manx",
            "v14RTTI.pets.Hamster"
    };

    @SuppressWarnings("unchecked")
    private static void loader()
    {
        try {
            for (String name : typeNames) {
                types.add((Class<? extends Pet>)Class.forName(name));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        loader();
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}
