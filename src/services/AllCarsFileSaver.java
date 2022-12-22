package services;

import path.getPath;
import utilities.Purchase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AllCarsFileSaver {
    private static final String EXCERCIES_2_GENERATION_ROOT = "src/createdFiles/allCarsFiles";
    public static final String  PURCHASE_OF = "purchase-of-";
    static List<Purchase> listOfPurchases = fileService.loadMainData(getPath.getPath());

    public static void run(Map<String, List<Purchase>> carsByCompany){
        createFilesGroupedByCompany(carsByCompany);
    }

    private static void createFilesGroupedByCompany(Map<String, List<Purchase>> carsByCompany) {
        for (Map.Entry<String, List<Purchase>> entry : carsByCompany.entrySet()) {
            Path companyPath = Paths.get(EXCERCIES_2_GENERATION_ROOT, PURCHASE_OF, entry.getKey()+".csv");
            List<String> data = entry.getValue().stream().map(purchaseMappingService::tocsvRow).toList();
            fileService.saveToFile(companyPath, data);
        }
    }
    public static void showFilesLength(){
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
    }


}

