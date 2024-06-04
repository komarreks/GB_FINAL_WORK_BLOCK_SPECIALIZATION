package ru.animalpack.model.pets;

import ru.animalpack.model.root.Pets;
import ru.animalpack.view.Menu;

public class Cat extends Pets {
    Boolean standartSize;

    public Cat(int id) {
        super(id, "Кошка");
        this.standartSize = Menu.enterAnswer("Кошка стандартного размера? [д/н]");
    }

    public Cat(String[] description){
        super(description);
        standartSize = Boolean.valueOf(description[7]);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getStringForSave() {
        return super.getStringForSave()+"&"+standartSize;
    }
}
