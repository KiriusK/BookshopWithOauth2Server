package ru.kirius.order_service.auxiliary;

import java.util.StringJoiner;

public class OrderAddress {
    private String country;
    private String region;
    private String city;
    private String street;
    private int houseNumber;
    private int flatNumber;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderAddress.class.getSimpleName() + "[", "]")
                .add("country='" + country + "'")
                .add("region='" + region + "'")
                .add("city='" + city + "'")
                .add("street='" + street + "'")
                .add("houseNumber=" + houseNumber)
                .add("flatNumber=" + flatNumber)
                .toString();
    }
}
