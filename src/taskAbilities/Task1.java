package taskAbilities;

import path.getPath;
import services.showDetails;
import utilities.Purchase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void printDatas(List<Purchase> listOfPurchases, Scanner sc) throws IOException {
        //użytkownik wybiera co chce zapisać
        listOfPurchases.forEach(System.out::println);
        System.out.println("wpisz 1 aby zapisać wszystko do pliku" );
        System.out.println("wpisz 2 aby zapisać do pliku wybrane kategorie");
        System.out.println("wpisz powrót by wrócić do menu wyborów tasków");
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if("1".equals(line)){
                saveThePurchaseList(listOfPurchases, sc);
            } else if ("2".equals(line)) {
                saveChosen(listOfPurchases, sc);
            } else if("powrót".equals(line)){
                showDetails.letsStart(sc, getPath.getPath());
            }
        }
    }

    private static void saveChosen(List<Purchase> listOfPurchases, Scanner sc) {
        System.out.println("wybierz kategorie (wpisz cyfre)");
        System.out.println("1 zapisz dane dotyczące samochodów");
       while (sc.hasNextLine()){
           String line = sc.nextLine();

           switch (line){
               case "1"-> SavingFilesService.makeCarFile(listOfPurchases,sc);
           }
       }
    }

    private static void saveThePurchaseList(List<Purchase> listOfPurchases, Scanner sc) throws IOException {
        Path savePath = Paths.get("src/path/fileFromTask1.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(savePath)) {
            for (Object o : listOfPurchases) {
                writer.write(o.toString());
                writer.newLine();
            }
            System.out.println("zapisano!");
            System.out.println("wpisz usuń aby usunąć utworzony plik");
            System.out.println("wpisz powrót aby wrócić do menu wyboru tasków");
            checkAnswer(sc, savePath);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    static void checkAnswer(Scanner sc, Path savePath) throws IOException {
        String line = sc.nextLine();
        if("usuń".equals(line)){
            Files.delete(savePath);
            System.out.println("Usunięto!");
            System.out.println("wpisz powrót aby wrócić do menu wyboru tasków");
            System.out.println("wpisz zapisz aby zapisać plik");
        } else if ("powrót".equals(line)) {
            showDetails.letsStart(sc, getPath.getPath());
        }
    }
}
