package utilities;

import java.util.StringJoiner;

public class Localization {
    private String country;
    private String city;

    public Localization(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Localization.class.getSimpleName() + "[", "]")
                .add("country='" + country + "'")
                .add("city='" + city + "'")
                .toString();
    }
}
