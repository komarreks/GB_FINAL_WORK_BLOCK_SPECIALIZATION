package ru.animalpack;


import ru.animalpack.view.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while (!menu.getExit()){
            menu.showMenu();
        }
    }
}