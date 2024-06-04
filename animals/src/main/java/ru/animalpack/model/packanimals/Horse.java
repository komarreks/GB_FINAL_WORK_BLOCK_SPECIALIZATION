package ru.animalpack.model.packanimals;

import ru.animalpack.model.root.PackAnimals;
import ru.animalpack.view.Menu;

public class Horse extends PackAnimals {
    Boolean pony;

    public Horse(int id) {
        super(id);
        this.pony = Menu.enterAnswer("Это лошадь пони? [д/н]");
    }
}
