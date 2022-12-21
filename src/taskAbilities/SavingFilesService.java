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

public abstract class SavingFilesService {
     static void makeCarFile(List<Purchase> listOfPurchases, Scanner sc) {
        System.out.println("Wybierz marke samochodu: ");
        System.out.println("bmw");
         while (sc.hasNextLine()){
             //użytkownik wybiera jaki plik chce zapisać na temat wybranej marki samochodu
             String line = sc.nextLine();
             if("bmw".equals(line)){
                 bmwSavingFile(listOfPurchases, sc);
             }
         }
    }

    private static void bmwSavingFile(List<Purchase> listOfPurchases, Scanner scanner) {
         //funkcja do zapisu danych o samochodzie marki bmw
        Path bmwPath = Paths.get("src/path/BmwFile.txt");
        List<Car> cars = listOfPurchases.stream()
                .map(Purchase::getCar)
                .filter(e -> "BMW".equals(e.getCompany()))
                .toList();
      try(BufferedWriter writer = Files.newBufferedWriter(bmwPath)) {
          for (Car car : cars) {
              writer.write(String.valueOf(car));
              writer.newLine();
          }
          System.out.println("zapisano!");
          System.out.println("wpisz powrót aby powrócić");
          System.out.println("wpisz usuń aby usunąć zapisany plik");

      } catch (IOException e) {
          throw new RuntimeException(e);
      }
    }
}
