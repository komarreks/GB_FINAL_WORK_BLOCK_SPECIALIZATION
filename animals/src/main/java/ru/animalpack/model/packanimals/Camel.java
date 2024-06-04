package ru.animalpack.model.packanimals;

import ru.animalpack.model.root.PackAnimals;
import ru.animalpack.view.Menu;

public class Camel extends PackAnimals {
    int humps;

    public Camel(int id) {
        super(id);
        this.humps = Integer.valueOf(Menu.enterDigitValue("Укажите количество горбов у верблюда",1,2,"Такого не бывает"));
    }
}
