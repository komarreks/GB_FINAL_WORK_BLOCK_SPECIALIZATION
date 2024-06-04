package ru.animalpack.view;

import ru.animalpack.controller.Repository;
import ru.animalpack.model.packanimals.Camel;
import ru.animalpack.model.packanimals.Donkey;
import ru.animalpack.model.packanimals.Horse;
import ru.animalpack.model.pets.Cat;
import ru.animalpack.model.pets.Dog;
import ru.animalpack.model.pets.Hamster;
import ru.animalpack.model.root.Animals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class Menu {
    private Boolean exit;
    private Repository repository;

    public Menu(){
        exit = false;
        repository = new Repository();
    }

    public void showMenu(){
        System.out.flush();
        System.out.println("            ПРОГРАММА УЧЕТА ЖИВОТНЫХ");
        System.out.println("************************************************************************************************************");
        System.out.println("| add (добавить) | all (вывести всех) | show s (команды животного) | add s (добавить команду) | exit (выйти)");
        System.out.println("************************************************************************************************************");
        System.out.println("| Всего животных: "+repository.countAnimals());
        System.out.println("************************************************************************************************************");
        System.out.print("Введите команду: ");
        commandListener();
    }

    private void commandListener(){
        String command = new Scanner(System.in).nextLine();

        if (command.equals("exit")){
            try {
                repository.saveData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("До скорых встреч!");
            exit = true;
        } else if (command.equals("add")) {
            addAnimal();
        } else if (command.equals("all")) {
            showAll();
        }else if (command.equals("show s")){
            shows();
        }else if (command.equals("add s")) {
            adds();
        }else {
            System.out.println("Проверьте ввод, команда не распознана, нажмите любую клавишу для продолжения");
            new Scanner(System.in).nextLine();
        }
    }

    private void adds() {
        System.out.println();
        System.out.println("Какому животному вы хотите добавить команду?");
        repository.showAll();
        System.out.println("________________________________________________________________________");
        System.out.print("Укажите номер:");
        int number = new Scanner(System.in).nextInt();
        Animals animal = repository.findAnimal(number);

        if (animal != null){
            String value = Menu.enterStringValue("Напишите команду", false);
            animal.addCommand(value);
            System.out.println("Нажмите любую клавишу");
            new Scanner(System.in).nextLine();
        }
    }

    private void addAnimal(){
        System.out.println("Укажите кого вы хотите добавить:\n" +
                "1 - Собака\n" +
                "2 - Кошка\n" +
                "3 - Хомяк\n" +
                "4 - Лошадь\n" +
                "5 - Верблюд\n" +
                "6 - Осел");

        int number = new Scanner(System.in).nextInt();

        switch (number){
            case 1: {repository.add(new Dog(repository.getNextAnimalID())); break;}
            case 2: {repository.add(new Cat(repository.getNextAnimalID())); break;}
            case 3: {repository.add(new Hamster(repository.getNextAnimalID())); break;}
            case 4: {repository.add(new Horse(repository.getNextAnimalID())); break;}
            case 5: {repository.add(new Camel(repository.getNextAnimalID())); break;}
            case 6: {repository.add(new Donkey(repository.getNextAnimalID())); break;}
            default:
                System.out.println("Такого типа животных не предусмотрено");
        }
    }

    public Boolean getExit() {
        return exit;
    }

    private void showAll(){
        repository.showAll();
        System.out.println("Нажмите любую клавишу");
        new Scanner(System.in).nextLine();
    }

    private void shows(){
        repository.showAll();
        System.out.print("Напишите номер животного, чьи навыки вы хотите увидеть: ");
        int number =  new Scanner(System.in).nextInt();
        repository.showSkills(number);
        System.out.println("Нажмите любую клавишу");
        new Scanner(System.in).nextLine();
    }

    public static String enterStringValue(String message, boolean mayBeEmpty){
        String value = "";

        while (value.isEmpty()){
            System.out.print(message+": ");
            value = (new Scanner(System.in)).nextLine();

            if (value.isEmpty() && mayBeEmpty){
                return value;
            }
        }

        return value;
    }

    public static String enterDigitValue(String message, int min, int max, String errorMessage){
        int value = 0;

        while (value == 0){
            System.out.print(message+": ");
            value = (new Scanner(System.in)).nextInt();
            if (value<min || value>max){
                System.out.println(errorMessage);
                value = 0;
            }
        }

        String stringValue = String.valueOf(value);

        if (stringValue.length() == 1){
            stringValue = "0" + stringValue;
        }

        return stringValue;
    }

    public static Date enterDate(){
        int now = Year.now().getValue();
        String year = enterDigitValue("Укажите год рождения",0, now,"Этот год не может быть указан");

        int maximumMonth = 12;
        if (year.equals(String.valueOf(now))){
            maximumMonth = LocalDate.now().getMonth().getValue();
        }

        String month = enterDigitValue("Укажите месяц рождения",1,maximumMonth,"Этот месяц не может быть указан");

        Calendar calendar = new GregorianCalendar();
        calendar.set(Integer.valueOf(year), Integer.valueOf(month),1);
        //int maximumDay = calendar.getActualMaximum(Integer.valueOf(month));

        int maximumDay = YearMonth.of(Integer.valueOf(year),Integer.valueOf(month)).lengthOfMonth();

        if (year.equals(String.valueOf(now)) && LocalDate.now().getMonth().getValue() == Integer.valueOf(month)){
            maximumDay = LocalDate.now().getDayOfMonth();
        }

        String day = enterDigitValue("Укажите число",1,maximumDay,"Этот день не может быть указан");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return dateFormat.parse(day+"/"+month+"/"+year);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> enterStringList(String message){
        String stringOfCommand = enterStringValue(message, true);

        List<String> list = new ArrayList<>();
        String[] strings = stringOfCommand.split(", ");

        list.addAll(Arrays.stream(strings).toList());

        return list;
    }

    public static Boolean enterAnswer(String message){
        String answer = "";

        while (answer.isBlank()){
            answer = enterStringValue(message, false);
            if (answer.toLowerCase().equals("д")){
                return true;
            } else if (answer.toLowerCase().equals("н")) {
                return false;
            }

            answer = "";
        }
        return null;
    }
}
