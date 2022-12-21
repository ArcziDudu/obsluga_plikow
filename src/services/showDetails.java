package services;
import taskAbilities.SavingFilesService;
import taskAbilities.Task1;
import utilities.Purchase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class showDetails {

    public static void printingFunction(String string, Scanner sc, Path path) throws IOException {

        if(string.equals("start")){
            letsStart(sc,path);
        } else {
            System.out.println("nie rozumiem");
        }

    }

    public static void letsStart(Scanner sc,Path path) throws IOException {
        System.out.println("Wybierz task");
        System.out.println("Wpisz task1 aby zobaczyć zmapowaną liste produktów z pliku csv");
        System.out.println("wpisz reset aby usunąć wszystkie utworzone pliki");
        System.out.println("pakiet 3");
        pakietValidator(sc, path); //funkcja sprawdzająca który task został wybrany
    }

    private static void pakietValidator(Scanner sc, Path path) throws IOException {

        List<Purchase> listOfPurchases = fileService.loadData(path);
        while (sc.hasNextLine()){
           String task = sc.nextLine();
           switch (task.trim()){
               case "task1"-> {
                   if (listOfPurchases != null) {
                       Task1.printDatas(listOfPurchases, sc);
                   }
               }
               case "reset" -> SavingFilesService.resetFiles();
               case "powrót" ->letsStart(sc, path);
               default -> System.out.println("nie rozumiem");
           }
        }
    }




}
