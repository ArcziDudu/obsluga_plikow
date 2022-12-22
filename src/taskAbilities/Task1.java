package taskAbilities;

import path.getPath;
import services.SavingFilesService;
import services.showDetails;
import utilities.Purchase;

import java.io.IOException;

import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void printDatas(List<Purchase> listOfPurchases, Scanner sc) throws IOException {
        //użytkownik wybiera co chce zapisać
        System.out.println();
        System.out.println("wpisz 1 aby wydrukowac liste w terminalu");
        System.out.println("wpisz 2 aby zapisać wszystko do pliku csv");
        System.out.println("wpisz 3 aby zobaczyć więcej funkcji (najpierw musisz zapisać plik)");
        System.out.println("wpisz 4 by wrócić do menu wyborów tasków");
        afterTask1Validator(listOfPurchases, sc);
    }

    private static void afterTask1Validator(List<Purchase> listOfPurchases, Scanner sc) throws IOException {
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            switch (line){
                case "1" -> {
                    listOfPurchases.forEach(System.out::println);
                    System.out.println("wpisz 2 aby zapisać wszystko do pliku csv");
                    System.out.println("wpisz 3 by wrócić do menu wyborów tasków");
                }
                case "2" -> {
                    SavingFilesService.saveThePurchaseList(listOfPurchases, sc);
                }
                case "3"->{
                    boolean exists = Files.exists(getPath.getPathSavedFromTak1());
                    if(!exists){
                        System.err.println("najpierw zapisz plik");
                        printDatas(listOfPurchases, sc);
                        return;
                    }
                    SavingFilesService.showMOre(sc);
                }
                case "4" -> showDetails.letsStart(sc, getPath.getPath());
                default -> System.out.println("nie rozumiem");
            }
        }
    }



}
