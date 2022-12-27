package services;

import path.getPath;
import taskAbilities.Task1;
import utilities.Car;
import utilities.Purchase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class SavingFilesService {
    static List<Purchase> listOfPurchases = fileService.loadMainData(getPath.getPath());

    public static void saveThePurchaseList(List<Purchase> listOfPurchases, Scanner sc) throws IOException {
        //użytkownik zapisuje zmapowane dane  do pliku
        Path savePath = getPath.getPathSavedFromTak1();
        try (BufferedWriter writer = Files.newBufferedWriter(savePath)) {
            for (Object o : listOfPurchases) {
                writer.write(o.toString());
                writer.newLine();
            }
            MenuAfterSavedMainFile(sc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void MenuAfterSavedMainFile(Scanner sc) throws IOException {
        //użytkownik wybiera co chce zrobić ze stworzonym plikiem
        System.out.println("zapisano!");
        System.out.println("wpisz 1 aby zobaczyć więcej funkcji");
        System.out.println("wpisz 2 aby usunąć utworzony plik");
        System.out.println("wpisz 3 aby wrócić do menu wyboru tasków");
        checkAnswer(sc);
    }

    static void checkAnswer(Scanner sc) throws IOException {
        //użytkownik wybiera co chce zrobić ze stworzonym plikiem
        String line = sc.nextLine();
        if ("2".equals(line)) {
            resetFiles();
            System.out.println("Usunięto!");
        } else if ("1".equals(line)) {
            showMOre(sc);
        } else if ("3".equals(line)) {
            showDetails.letsStart(sc, getPath.getPath());
        }
    }

    public static void resetFiles() throws IOException {
        //usuwa wszystkie pliki
        File[] files = new File("src/createdFiles").listFiles();
        File[] files1 = new File("src/createdFiles/allCarsFiles/purchase-of-").listFiles();
        File[] files2 = new File("src/createdFiles/reportDatas").listFiles();
        for (File file : files1) {
            if (file.isFile()) {
                file.delete();
            }
        }
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            }
        }
        assert files2 != null;
        for (File file : files2) {
            if (file.isFile()) {
                file.delete();
            }
        }
        System.out.println("pomyślnie usunięto!");
        System.out.println("Wpisz 1 aby zobaczyć zmapowaną liste produktów z pliku csv");

    }

    public static void showMOre(Scanner sc) {
        //pokazuje co mozna zrobic z plikiem
        System.out.println("1 - aby sprawdź czy w pliku występuje wybrany model samochodu");
        System.out.println("2 - zapisz do plików wszystkie marki samochodów");
        System.out.println("3 - powrót");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            switch (line) {
                case "1" -> {
                    System.out.println("wpisz marke samochodu np mazda");
                    SavingFilesService.checkCarModel(sc);
                }
                case "2" -> saveAll(sc);
                case "3" -> {
                    try {
                        Task1.printDatas(listOfPurchases, sc);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void saveAll(Scanner scanner) {
        //zapisuje marki samochodów do osobnych plików
        Map<String, List<Purchase>> carsByCompany = listOfPurchases.stream()
                .collect(Collectors
                        .groupingBy(p -> p.getCar().getCompany()));
        AllCarsFileSaver.run(carsByCompany);
        System.out.println("1 - pokaż rozmiary poszczególnych plików malejąco");
        System.out.println("2 - aby wygenerować raport sreniej ceny sprzedaży poszczególnych marek");
        System.out.println("3 - aby wygenerować raport daty sprzedaży poszczególnych marek");
        System.out.println("4 - powrót");
        checkAnswerFromAllSaver(scanner, carsByCompany);
    }

    private static void checkAnswerFromAllSaver(Scanner scanner, Map<String, List<Purchase>> carsByCompany) {
        //menu do dalszych operacji na utworoznych plikach
        Map<String, List<Purchase>> carsByCompany2 = listOfPurchases.stream()
                .collect(Collectors
                        .groupingBy(p -> p.getCar().getCompany()));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case "1" -> {
                    AllCarsFileSaver.showFilesLength();
                    return;
                }
                case "2" -> AllCarsFileSaver.makeRaport(scanner);
                case "3" -> AllCarsFileSaver.dateRapoert(scanner);
                case "4" -> showMOre(scanner);
                default -> System.out.println("nie rozumiem");

            }
        }
    }

    public static void checkCarModel(Scanner sc) {
        //FUNCKJA SPRAWDZAJĄCA CZY WPISANY MODEL WYSTEPUJE W PLIKU

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String car = stringCar(line);

            assert listOfPurchases != null;
            boolean b = listOfPurchases.stream()
                    .map(e -> e.getCar().getCompany())
                    .filter(car::equals).anyMatch(car::equals);
            if (b) {
                System.out.println("w pliku jest podany samochód. Wyświetlić informacje? T/N");
                while (sc.hasNextLine()) {
                    String lineTN = sc.nextLine().toUpperCase();
                    switch (lineTN) {
                        case "T" -> {
                            showInfoCar(listOfPurchases, car, sc);
                            return;
                        }
                        case "N" -> System.out.println("czekam");
                    }

                }
            } else {
                System.err.println("w pliku nie ma podanego samochodu. Spróbuj ponownie");
            }
        }
    }

    private static void showInfoCar(List<Purchase> listOfPurchases, String car, Scanner scanner) {
        //funkcja do wyswietlania i zapisu pliku z wybranym samochodem
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
        //funckca sterująca
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case "1" -> saveOneCar(cars);
                case "2" -> {
                    System.out.println("wpisz marke samochodu np mazda");
                    SavingFilesService.checkCarModel(scanner);
                }
                case "3" -> {
                    try {
                        showDetails.letsStart(scanner, getPath.getPath());
                    } catch (IOException e) {

                    }
                }
            }
        }
    }

    private static void saveOneCar(List<Car> cars) {
        //funkcja do  zapisu wybranego pojazdu
        Optional<String> first = cars.stream().map(e -> e.getCompany().toString()).findFirst();
        String fileName = first.get();
        Path carFilePath = Paths.get("src/createdFiles/" + fileName + ".csv");
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
        //funkcja sprawdzająca czy wybrany samochod ma trzy litery
        String car;
        car = line.substring(0, 1).toUpperCase() + line.substring(1);
        if (car.length() == 3) {
            car = car.toUpperCase();
        }
        return car;
    }

}
