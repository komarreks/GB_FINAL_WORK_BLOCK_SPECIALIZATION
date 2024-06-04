package ru.animalpack.model.root;

import ru.animalpack.view.Menu;

public abstract class PackAnimals extends Animals{
    int maximumWeight;
    String purpose;

    public PackAnimals(int id) {
        super(id);
        this.purpose = Menu.enterStringValue("Напишите для чего используется животное");
    }
}
