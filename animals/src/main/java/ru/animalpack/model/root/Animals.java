package ru.animalpack.model.root;

import ru.animalpack.view.Menu;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public abstract class Animals {
    int id;
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

    public Animals(int id){
        this.id        = id;
        this.name      = Menu.enterStringValue("Укажите кличку");
        this.birthDate = Menu.enterDate();
        this.color     = Menu.enterStringValue("Укажите цвет в свободной форме");
        this.breed     = Menu.enterStringValue("Укажите породу");
        this.commands  = Menu.enterStringList("Укажите, какие команды умеет выполнять животное, вводите команды через пробел");
    }

    public int getId(){
        return id;
    }
}
