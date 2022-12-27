package services;

import utilities.Car;
import utilities.Localization;
import utilities.Person;
import utilities.Purchase;

import java.math.BigDecimal;
import java.time.LocalDate;

public class purchaseMappingService {
    public static Purchase mapPurchase(String input) {
//id,first_name,last_name,email,ip_address,color,car_vin,car_company,car_model,car_model_year,car_price,country,city,date
        String[] word = input.split(",");
        long id = Long.parseLong(word[0]);
        //person
        String imie = word[1];
        String nazwisko = word[2];
        String email = word[3];
        String ip = word[4];

        //car
        String color = word[5];
        String vin = word[6];
        String company = word[7];
        String model = word[8];
        String model_year = word[9];
        BigDecimal price = new BigDecimal(word[10].replace("\"", "").substring(1));
        String country = word[11];
        String city = word[12];
        LocalDate date = LocalDate.parse(word[13]);

        return new Purchase(
                id,
                new Person(imie, nazwisko, email, ip),
                new Car(color, vin, company, model, model_year, price),
                new Localization(country, city), date);
    }

    public static String tocsvRow(Purchase purchase) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                purchase.getId(),
                purchase.getPerson().getImie(),
                purchase.getPerson().getNazwisko(),
                purchase.getPerson().getEmail(),
                purchase.getPerson().getIp(),
                purchase.getCar().getColor(),
                purchase.getCar().getVin(),
                purchase.getCar().getCompany(),
                purchase.getCar().getModel(),
                purchase.getCar().getModel_year(),
                purchase.getCar().getPrice(),
                purchase.getLocalization().getCountry(),
                purchase.getLocalization().getCity(),
                purchase.getDate()
        );

    }


}
