package services;

import path.getPath;
import utilities.Purchase;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class AllCarsFileSaver {
    private static final String EXCERCIES_2_GENERATION_ROOT = "src/createdFiles/allCarsFiles";
    public static final String PURCHASE_OF = "purchase-of-";

    public static void run(Map<String, List<Purchase>> carsByCompany) {
        createFilesGroupedByCompany(carsByCompany);
    }

    private static void createFilesGroupedByCompany(Map<String, List<Purchase>> carsByCompany) {
        for (Map.Entry<String, List<Purchase>> entry : carsByCompany.entrySet()) {
            Path companyPath = Paths.get(EXCERCIES_2_GENERATION_ROOT, PURCHASE_OF, entry.getKey() + ".csv");
            List<String> data = entry.getValue().stream().map(purchaseMappingService::tocsvRow).toList();
            fileService.saveToFile(companyPath, data);
        }
    }

    public static void showFilesLength() {
        Map<String, Long> fileSizes = new HashMap<>();

        File dir = new File("src/createdFiles/allCarsFiles/purchase-of-");
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isFile()) {
                    fileSizes.put(file.getName(), file.length());
                }
            }
        }
        Map<String, Long> sortedFileSizes = FileSizeSorter.sortFileSizes(fileSizes);
        for (Map.Entry<String, Long> entry : sortedFileSizes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " bytes");
        }
        System.out.println("1 - powrót");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            switch (line) {
                case "1" -> SavingFilesService.showMOre(sc);
                default -> System.out.println("nie rozumiem");
            }
        }
    }

    public static void makeRaport(Scanner scanner) {
        List<Purchase> listOfPurchases = fileService.loadMainData(getPath.getPath());
        Map<String, List<Purchase>> mapByCompany = listOfPurchases.stream()
                .collect(Collectors.groupingBy(e -> e.getCar().getCompany()));
        Map<String, Map<String, List<Purchase>>> mapByCompanyAndModel = mapByCompany.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .collect(Collectors.groupingBy(p -> p.getCar().getModel()))
                ));
        Map<String, Map<String, Pair<BigDecimal, Long>>> reportMap = mapByCompanyAndModel
                .entrySet().stream().collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().entrySet().stream()
                                        .collect(Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        e1 -> buildPair(e1.getValue())

                                                )
                                        )
                        )
                );
        List<String> reportData = new ArrayList<>(reportMap.entrySet().stream()
                .map(eExternal -> eExternal.getValue().entrySet().stream()
                        .map(eInternal -> getRawRow(eInternal, eExternal.getKey(), eInternal.getKey()))
                        .toList()
                ).flatMap(Collection::stream)
                .toList());
        for (int i = 0; i < reportData.size(); i++) {
            reportData.set(i, "" + (i + 1) + ", " + reportData.get(i));
        }
        System.out.println("Wygenerowano! ");
        System.out.println();
        System.out.println("1 - pokaż raport| marka - model - srednia cena sprzedaży - ilosc sprzedanych");
        System.out.println("2 - zapisz do pliku");
        while (scanner.hasNextLine()) {
            String list = scanner.nextLine();
            switch (list) {
                case "1" -> {
                    reportData.forEach(System.out::println);
                    System.out.println("2 - zapisz do pliku");
                    System.out.println("3 - powrót");
                }
                case "2" -> {
                    savePath(reportData);
                    System.out.println("zapisano!");
                    System.out.println("3 - powrót");
                }
                case "3" -> SavingFilesService.saveAll(scanner);

            }
        }
    }

    private static void savePath(List<String> reportData) {
        Path path = Paths.get("src/createdFiles/reportDatas/report.csv");
        fileService.saveToFile(path, reportData, "id,company,model,avarage_price, count");
    }


    private static String getRawRow(Map.Entry<String, Pair<BigDecimal, Long>> eInternal, String company, String model) {
        return String.format("%s, %s, %s, %s ", company, model, eInternal.getValue().getT(), eInternal.getValue().getU());
    }

    private static Pair<BigDecimal, Long> buildPair(List<Purchase> purchaseList) {
        long count = purchaseList.size();
        BigDecimal avaragePrice = purchaseList.stream()
                .map(e -> e.getCar().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
        return new Pair<>(avaragePrice, count);
    }

    public static void dateRapoert(Scanner scanner) {
        System.out.println("zapisano !");
        System.out.println("2 - powrót");
        List<Purchase> listOfPurchases = fileService.loadMainData(getPath.getPath());
        TreeMap<LocalDate, Long> mapByDate = listOfPurchases.stream().collect(Collectors.groupingBy(
                Purchase::getDate,
                TreeMap::new,
                Collectors.counting()
        ));
        AtomicInteger byDateCounter = new AtomicInteger(1);
        List<String> list = mapByDate.entrySet().stream()
                .map(e -> String.format("%s,%s,%s", byDateCounter.getAndIncrement(), e.getKey(), e.getValue())).toList();
        generateReport(list, "ByDate");


    }

    private static void generateReport(List<String> list, String byDate) {
        Path path = Paths.get("src/createdFiles/reportDatas/reportDate.csv");
        fileService.saveToFile(path, list, byDate);
    }
}


