package utilities;

import java.math.BigDecimal;

public class Car {
    private String color;
    private String vin;
    private String company;
    private String model;
    private String model_year;
    private BigDecimal price;

    public Car(String color, String vin, String company, String model, String model_year, BigDecimal price) {
        this.color = color;
        this.vin = vin;
        this.company = company;
        this.model = model;
        this.model_year = model_year;
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public String getColor() {
        return color;
    }

    public String getVin() {
        return vin;
    }

    public String getModel_year() {
        return model_year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        String sb = "Car{" + "color='" + color + '\'' +
                ", vin='" + vin + '\'' +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", model_year='" + model_year + '\'' +
                ", price=" + price +
                '}';
        return sb;
    }
}
