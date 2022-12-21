package utilities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Purchase {
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
