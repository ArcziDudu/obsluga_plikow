package services;

import utilities.Purchase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class fileService {
    public static List<Purchase> loadData(Path path){
        try {
             return Files.lines(path, StandardCharsets.UTF_8)
                     .skip(1)
                     .map(purchaseMappingService::mapPurchase)
                     .toList();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
