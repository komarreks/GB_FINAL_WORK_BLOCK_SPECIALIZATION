package ru.animalpack.controller;

import ru.animalpack.model.packanimals.Camel;
import ru.animalpack.model.packanimals.Donkey;
import ru.animalpack.model.packanimals.Horse;
import ru.animalpack.model.pets.Cat;
import ru.animalpack.model.pets.Dog;
import ru.animalpack.model.pets.Hamster;
import ru.animalpack.model.root.Animals;

import java.io.*;
import java.util.*;

public class Repository {
    private List<Animals> animals;

    public Repository()  {
        animals = new ArrayList<>();

        try {
            LoadData();
        }catch (IOException e){
            try {
                saveData();
                LoadData();
            }catch (IOException e1){
                System.out.println("в процессе инициализации БД произошла ошибка: "+e1.getMessage());
            }
        }
    }

    public void LoadData() throws IOException {
        String path = "animals.txt";

        BufferedReader br = new BufferedReader(new FileReader(path));

        String line = br.readLine();

        while (line != null) {
            String[] animal = line.split("&");
            switch (animal[0]) {
                case "Верблюд": {add(new Camel(animal)); break;}
                case "Осел": {add(new Donkey(animal)); break;}
                case "Лошадь":{add(new Horse(animal)); break;}
                case "Кошка":{add(new Cat(animal)); break;}
                case "Собака":{add(new Dog(animal)); break;}
                case "Хомяк":{add(new Hamster(animal)); break;}
            }
            line = br.readLine();
        }

    }

    public void saveData() throws IOException {
        String path = "animals.txt";
        File file = new File(path);

        StringBuilder stringBuilder = new StringBuilder();

        for (Animals animal : animals) {
            stringBuilder.append(animal.getStringForSave()).append("\n");
        }

        Writer writer = new FileWriter(file);

        writer.write(stringBuilder.toString());
        writer.close();
    }

    public int countAnimals() {
        return animals.size();
    }

    public void add(Animals animal) {
        animals.add(animal);
    }

    public int getNextAnimalID() {
        if (countAnimals() == 0){
            return 1;
        }

        Collections.sort(animals, (a1, a2) -> a1.getId() - a2.getId());

        return animals.get(animals.size() - 1).getId() + 1;
    }

    public void showAll(){
        System.out.println("*******************************************************");
        for (Animals animal : animals) {
            System.out.println(animal.toString());
        }
        System.out.println("*******************************************************");

    }

    public Animals findAnimal(int number){
        for (Animals animal : animals) {
            if (animal.getId() == number){
                return animal;
            }
        }

        System.out.println("Животного с таким номером нет");
        return null;
    }

    public void addSkill(Animals animal, String command){
        animal.addCommand(command);
        System.out.println("Команда добавлена");
    }

    public void showSkills(int number){
        Animals animal = findAnimal(number);

        if (animal!=null){
            StringBuilder sb = new StringBuilder();
            System.out.println();
            sb.append(animal.getRuCustomClass() + " по кличке: " + animal.getName()+"\n");

            if (animal.getCountCommands() > 0){
                sb.append("умеет выполнять следующие команды:\n");
            }
            sb.append(animal.getCommandsAsString());
            System.out.println(sb.toString());
        }
    }
}
