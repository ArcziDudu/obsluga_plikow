package utilities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Purchase {
    public  static final String  CSV_HEADER =
            "id,first_name,last_name,email,ip_address,color,car_vin," +
                    "car_company,car_model,car_model_year,car_price,country,city,date";
    private long id;
    private Person person;
    private Car car;
    private Localization localization;
    private LocalDate date;

    public Purchase(long id, Person person, Car car, Localization localization, LocalDate date) {
        this.id = id;
        this.person = person;
        this.car = car;
        this.localization = localization;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Localization getLocalization() {
        return localization;
    }

    public LocalDate getDate() {
        return date;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        String sb = "Purchase{" + "id=" + id +
                ", person=" + person +
                ", car=" + car +
                ", localization=" + localization +
                ", date=" + date +
                '}';
        return sb;
    }
}
