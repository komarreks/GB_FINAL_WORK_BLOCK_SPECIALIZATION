package ru.animalpack.model.packanimals;

import ru.animalpack.model.root.PackAnimals;

public class Donkey extends PackAnimals {
    public Donkey(int id) {
        super(id, "Осел");
    }

    public Donkey(String[] description){
        super(description);
    }

    @Override
    public String getStringForSave() {
        return super.getStringForSave();
    }
}
