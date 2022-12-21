package services;
import utilities.Purchase;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class showDetails {

    public static void printingFunction(String string, Scanner sc, Path path){

        if(string.equals("start")){
            letsStart(sc,path);
        } else {
            System.out.println("nie rozumiem");
        }

    }

    private static void letsStart(Scanner sc,Path path) {
        System.out.println("Wybierz task");
        System.out.println("Wpisz task1 aby zobaczyć zmapowaną liste produktów z pliku csv");
        System.out.println("pakiet 2");
        System.out.println("pakiet 3");
        pakietValidator(sc, path); //funkcja sprawdzająca który task został wybrany
    }

    private static void pakietValidator(Scanner sc, Path path) {
        List<Purchase> listOfPurchases = fileService.loadData(path);
        while (sc.hasNextLine()){
           String task = sc.nextLine();
           switch (task){
               case "task1"-> listOfPurchases.forEach(System.out::println);
               case "powrót" ->letsStart(sc, path);
               default -> System.out.println("nie rozumiem");
           }
        }
    }


}
