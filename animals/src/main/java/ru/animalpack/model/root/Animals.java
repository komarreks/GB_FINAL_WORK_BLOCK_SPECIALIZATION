package ru.animalpack.model.root;

import ru.animalpack.view.Menu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    String ruCustomClass;

    public String getRuCustomClass(){
        return ruCustomClass;
    }

    public String getName(){
        return name;
    }

    public int getCountCommands(){
        return commands.size();
    }

    public void addCommand(String command) {
        if (!commands.contains(command)){
            commands.add(command);
            System.out.println("Команда добавлена");
        }else {
            System.out.println("Такая команда уже есть");
        }
    }

    public String getCommandsAsString(){
        if (commands.size() == 0){
            return "Животное ничему не обучено";
        }

        StringBuilder sb = new StringBuilder();
        String comma = "";
        for (String command : commands){
            sb.append(comma+command);
            comma = ", ";
        }

        return sb.toString();
    }

    private String getDataAsString(Boolean forPrint){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (forPrint){
            sdf = new SimpleDateFormat("dd MMMM yyyy");
        }

        return sdf.format(birthDate);
    }

    public Animals(int id, String ruCustomClass){
        this.id            = id;
        this.name          = Menu.enterStringValue("Укажите кличку", false);
        this.birthDate     = Menu.enterDate();
        this.color         = Menu.enterStringValue("Укажите цвет в свободной форме", false);
        this.breed         = Menu.enterStringValue("Укажите породу", false);
        this.commands      = Menu.enterStringList("Укажите, какие команды умеет выполнять животное, вводите команды через запятую");
        this.ruCustomClass = ruCustomClass;
    }

    public Animals(String[] description){
        ruCustomClass = description[0];
        id = Integer.parseInt(description[1]);
        name = description[2];

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            birthDate = dateFormat.parse(description[3]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        color = description[4];

        commands = new ArrayList<>();
        if (!description[5].equals("Животное ничему не обучено")){
            commands = List.of(description[5].split(", "));
        }

    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Номер: "+id+ " ("+ruCustomClass+")" +
                " Кличка: "+ name+
                " Дата рождения: "+ getDataAsString(true);
    }

    public String getStringForSave(){
        return ruCustomClass+"&"+
               id+"&"+
               name+"&"+
               getDataAsString(false)+"&"+
               color+"&"+
               getCommandsAsString();
    }
}
