package services;

import utilities.Purchase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class fileService {
    public static List<Purchase> loadMainData(Path path){
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
    public  static void saveToFile(Path path, List<String> data){
        saveToFile(path,data, Purchase.CSV_HEADER);
    }
    public static void saveToFile(Path path, List<String> data, String header){
        try{
            Files.createDirectories(path.subpath(0,path.getNameCount()-1));
        } catch (IOException e) {
            System.err.printf("Error for path: %s, message: %s%n", path, e.getMessage());
        }
        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset())){
            writer.write(header);
            writer.newLine();
            for (String row : data) {
                writer.write(row);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
