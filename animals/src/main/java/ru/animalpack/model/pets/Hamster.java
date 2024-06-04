package ru.animalpack.model.pets;

import ru.animalpack.model.root.Pets;

public class Hamster extends Pets {
    public Hamster(int id) {
        super(id, "Хомяк");
    }

    public Hamster(String[] description){
        super(description);
    }

    @Override
    public String getStringForSave() {
        return super.getStringForSave();
    }
}
