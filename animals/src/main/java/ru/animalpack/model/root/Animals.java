package ru.animalpack.model.root;

import java.util.Date;
import java.util.List;

public abstract class Animals {
    String name;
    Date birthDate;
    List<String> commands;
    String color;
    String breed;

    public void addCommand(String command) {
        if (!commands.contains(command)){
            commands.add(command);
            System.out.println("Команда добавлена");
        }else {
            System.out.println("Такая команда уже есть");
        }
    }
}
