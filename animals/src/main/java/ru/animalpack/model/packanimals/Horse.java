package ru.animalpack.model.packanimals;

import ru.animalpack.model.root.PackAnimals;
import ru.animalpack.view.Menu;

public class Horse extends PackAnimals {
    Boolean pony;

    public Horse(int id) {
        super(id, "Лошадь");
        this.pony = Menu.enterAnswer("Это лошадь пони? [д/н]");
    }

    public Horse(String[] description) {
        super(description);
        pony = Boolean.valueOf(description[7]);
    }

    @Override
    public String getStringForSave() {
        return super.getStringForSave()+"&"+pony;
    }
}
