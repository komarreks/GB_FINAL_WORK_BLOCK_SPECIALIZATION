package ru.animalpack.model.root;

import ru.animalpack.view.Menu;

public abstract class PackAnimals extends Animals{
    int maximumWeight;
    String purpose;

    public PackAnimals(int id, String ruCustomClass) {
        super(id, ruCustomClass);
        this.purpose = Menu.enterStringValue("Напишите для чего используется животное", false);
    }

    public PackAnimals(String[] description) {
        super(description);
        purpose = description[6];
    }

    @Override
    public String getStringForSave() {
        return super.getStringForSave()+"&"+this.purpose;
    }
}
