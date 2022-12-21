package taskAbilities;

import path.getPath;
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
        System.out.println("wpisz drukuj aby wydrukowac liste w terminalu");
        System.out.println("wpisz 2 aby zapisać wszystko do pliku csv");
        System.out.println("wpisz więcej aby zobaczyć więcej funkcji");
        System.out.println("wpisz powrót by wrócić do menu wyborów tasków");

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            switch (line){
                case "drukuj" -> {
                    listOfPurchases.forEach(System.out::println);
                    System.out.println("wpisz 2 aby zapisać wszystko do pliku csv");
                    System.out.println("wpisz powrót by wrócić do menu wyborów tasków");
                }

                case "2" -> {
                    SavingFilesService.saveThePurchaseList(listOfPurchases, sc);
                }
                case "więcej"->{
                    boolean exists = Files.exists(getPath.getPathSavedFromTak1());
                    if(!exists){
                        System.err.println("najpierw zapisz plik");
                        printDatas(listOfPurchases, sc);
                        return;
                    }
                    showMOre(sc);
                }
                case "powrót" -> showDetails.letsStart(sc, getPath.getPath());
                default -> System.out.println("nie rozumiem");
            }
        }
    }

    public static void showMOre(Scanner sc) {
        System.out.println("wpisz marka aby sprawdzić czy w pliku występuje wybrany model samochodu");
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            switch (line){
                case "marka" ->{
                    System.out.println("wpisz marke samochodu np mazda");
                    SavingFilesService.checkCarModel(sc);
                }
            }
        }
    }

}
