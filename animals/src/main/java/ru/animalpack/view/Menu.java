package ru.animalpack.view;

import java.util.Scanner;

public class Menu {
    private Boolean exit;

    public Menu(){
        exit = false;
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
}
