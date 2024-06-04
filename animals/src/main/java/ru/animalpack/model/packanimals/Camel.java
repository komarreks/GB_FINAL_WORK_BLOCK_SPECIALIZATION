package ru.animalpack.model.packanimals;

import ru.animalpack.model.root.PackAnimals;
import ru.animalpack.view.Menu;

public class Camel extends PackAnimals {
    int humps;

    public Camel(int id) {
        super(id, "Верблюд");
        this.humps = Integer.valueOf(Menu.enterDigitValue("Укажите количество горбов у верблюда",1,2,"Такого не бывает"));
    }

    public Camel(String[] description){
        super(description);
        humps = Integer.valueOf(description[7]);
    }

    @Override
    public String getStringForSave() {
        return super.getStringForSave()+"&"+humps;
    }
}
