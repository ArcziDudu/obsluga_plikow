package taskAbilities;
import path.getPath;
import services.showDetails;
import utilities.Car;
import utilities.Purchase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class SavingFilesService {
    public static void saveThePurchaseList(List<Purchase> listOfPurchases, Scanner sc) throws IOException {
        //użytkownik zapisuje zmapowane dane  do pliku
        Path savePath = Paths.get("src/createdFiles/fileFromTask1.csv");
        try(BufferedWriter writer = Files.newBufferedWriter(savePath)) {
            for (Object o : listOfPurchases) {
                writer.write(o.toString());
                writer.newLine();
            }
            System.out.println("zapisano!");
            System.out.println("wpisz usuń aby usunąć utworzony plik");
            System.out.println("wpisz powrót aby wrócić do menu wyboru tasków");
            checkAnswer(sc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void checkAnswer(Scanner sc) throws IOException {
        //użytkownik wybiera co chce zrobić z usuniętym plikiem
        String line = sc.nextLine();
        if("usuń".equals(line)){
            resetFiles();
            System.out.println("Usunięto!");
        } else if ("powrót".equals(line)) {
            showDetails.letsStart(sc, getPath.getPath());
        }
    }
    public static void resetFiles() throws IOException {
        System.out.println("pomyślnie usunięto!");
        Files.deleteIfExists(Path.of("src/createdFiles/fileFromTask1.csv"));
    }
}
