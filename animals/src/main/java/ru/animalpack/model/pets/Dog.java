package ru.animalpack.model.pets;

import ru.animalpack.model.root.Pets;
import ru.animalpack.view.Menu;

public class Dog extends Pets {
    Boolean decorative;

    public Dog(int id) {
        super(id, "Собака");
        this.decorative = Menu.enterAnswer("Собака декоративной породы? [д/н]");
    }
}
