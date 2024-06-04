package ru.animalpack.view;

import ru.animalpack.controller.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
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
        System.out.println("**************************************************");
        System.out.println("| add (добавить) | exit (выйти) |                 ");
        System.out.println("**************************************************");
        System.out.print("Введите команду: ");
        commandListener();
    }

    private void commandListener(){
        String command = new Scanner(System.in).nextLine();

        if (command.equals("exit")){
            System.out.println("До скорых встреч!");
            exit = true;
        }else {
            System.out.println("Проверьте ввод, команда не распознана, нажмите любую клавишу для продолжения");
            new Scanner(System.in).nextLine();
        }
    }

    public Boolean getExit() {
        return exit;
    }

    public static String enterStringValue(String message){
        String value = "";

        while (value.isEmpty()){
            System.out.print(message+": ");
            value = (new Scanner(System.in)).nextLine();
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
        int maximumDay = calendar.getActualMaximum(Integer.valueOf(month));

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
        String stringOfCommand = enterStringValue(message);

        List<String> list = List.of(stringOfCommand.split(" "));

        return list;
    }
}
