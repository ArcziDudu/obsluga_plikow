package taskAbilities;

import path.getPath;
import services.showDetails;
import utilities.Car;
import utilities.Purchase;
import services.fileService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public abstract class SavingFilesService {
    public static void saveThePurchaseList(List<Purchase> listOfPurchases, Scanner sc) throws IOException {
        //użytkownik zapisuje zmapowane dane  do pliku
        Path savePath = getPath.getPathSavedFromTak1();
        try(BufferedWriter writer = Files.newBufferedWriter(savePath)) {
            for (Object o : listOfPurchases) {
                writer.write(o.toString());
                writer.newLine();
            }
            System.out.println("zapisano!");
            System.out.println("wpisz więcej aby zobaczyć więcej funkcji");
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
        } else if ("więcej".equals(line)) {
            Task1.showMOre(sc);
        } else if ("powrót".equals(line)) {
            showDetails.letsStart(sc, getPath.getPath());
        }
    }
    public static void resetFiles() throws IOException {
        File[] files = new File("src/createdFiles").listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                file.delete();
            } else if (file.isFile()) {
                file.delete();
            }
        }
        System.out.println("pomyślnie usunięto!");

    }

    public static void checkCarModel(Scanner sc) {
        List<Purchase> listOfPurchases = fileService.loadData(getPath.getPath());
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String car = stringCar(line);

            assert listOfPurchases != null;
            boolean b = listOfPurchases.stream()
                    .map(e -> e.getCar().getCompany())
                    .filter(car::equals).anyMatch(car::equals);
            if(b){
                System.out.println("w pliku jest podany samochód. Wyświetlić informacje? T/N");
                while (sc.hasNextLine()){
                    String lineTN = sc.nextLine().toUpperCase();
                    switch (lineTN){
                        case "T"->{
                          showInfoCar(listOfPurchases, car, sc);
                          return;
                        }
                        case "N"-> System.out.println("czekam");
                    }

                }
            }else {
                System.err.println("w pliku nie ma podanego samochodu. Spróbuj ponownie");
            }


        }
    }

    private static void showInfoCar(List<Purchase> listOfPurchases, String car, Scanner scanner) {
        List<Car> cars = listOfPurchases.stream()
                .map(Purchase::getCar)
                .filter(e -> car.equals(e.getCompany()))
                .toList();
      cars.forEach(System.out::println);
        System.out.println();
        System.out.println("ilość samochódów danej marki w pliku: " + cars.size());
        System.out.println();
        System.out.println("wybierz cyfre:");
        System.out.println("1 - zapisz do pliku");
        System.out.println("2 - powrót do wybory marki");
        System.out.println("3 - powrót do menu");
        choseNumber(scanner, cars);
    }

    private static void choseNumber(Scanner scanner, List<Car> cars) {
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            switch (line){
                case "1"->saveOneCar(cars);
                case "2"->Task1.showMOre(scanner);
                case "3"-> {
                    try {
                        showDetails.letsStart(scanner, getPath.getPath());
                    } catch (IOException e) {

                    }
                }
            }
        }
    }

    private static void saveOneCar(List<Car> cars) {
        Optional<String> first = cars.stream().map(e -> e.getCompany().toString()).findFirst();
        String fileName = first.get();
        Path carFilePath = Paths.get("src/createdFiles/"+fileName+".csv");
        try (BufferedWriter writer = Files.newBufferedWriter(carFilePath)) {
            for (Object o : cars) {
                writer.write(o.toString());
                writer.newLine();
            }
            System.out.println("zapisano!");
            System.out.println("2 - powrót do wybory marki");
            System.out.println("3 - powrót do menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String stringCar(String line) {
        String car;
        car = line.substring(0, 1).toUpperCase() + line.substring(1);
        if(car.length()==3){
            car = car.toUpperCase();
        }
        return car;
    }
}
