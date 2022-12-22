import path.getPath;
import services.showDetails;

import java.io.IOException;
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