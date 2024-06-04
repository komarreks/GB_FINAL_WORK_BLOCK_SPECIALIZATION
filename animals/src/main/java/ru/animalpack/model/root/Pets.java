package ru.animalpack.model.root;

import ru.animalpack.view.Menu;

public abstract class Pets extends Animals{
    Boolean homeMaintenanceOnly;

    public Pets(int id, String ruCustomClass){
        super(id, ruCustomClass);
        homeMaintenanceOnly = Menu.enterAnswer("Только для домашнего содержания? [д/н");
    }
}
