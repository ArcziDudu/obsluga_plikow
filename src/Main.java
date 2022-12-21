import services.showDetails;
import  path.getPath;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("wpisz start aby rozpocząć");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            String test =  sc.nextLine().trim().toLowerCase();
            showDetails.printingFunction(test, sc,getPath.getPath());
        }
    }
}