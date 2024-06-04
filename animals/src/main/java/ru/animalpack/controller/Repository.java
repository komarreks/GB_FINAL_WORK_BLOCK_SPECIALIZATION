package ru.animalpack.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.animalpack.model.root.Animals;

import java.io.File;
import java.io.IOException;
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
        String path = "animals.json";

        ObjectMapper mapper = new ObjectMapper();

        animals = mapper.readValue(new File(path), new TypeReference<List<Animals>>(){});
    }

    public void saveData() throws IOException {
        String path = "animals.json";
        File file = new File(path);

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(file, animals);
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
        System.out.println("Нажмите любую клавишу");
        new Scanner(System.in).nextLine();
    }
}
