package ru.kirius.order_database_service.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.kirius.order_database_service.auxiliary.OrderAddress;
import ru.kirius.order_database_service.enums.OrderStatus;

import java.time.LocalDate;

@Table("orders")
public class Order {

    @Id
    private Long id;

    @Column("ordernumber")
    private long orderNumber;

    @Column("orderdate")
    private LocalDate orderDate;

    @Column("consumername")
    private String consumerName;

    @Column("consumersurname")
    private String consumerSurName;

    @Column("country")
    private String country;

    @Column("region")
    private String region;

    @Column("city")
    private String city;

    @Column("street")
    private String street;

    @Column("housenumber")
    private int houseNumber;

    @Column("flatnumber")
    private int flatNumber;

    @Column("cost")
    private double cost;

    @Column("status")
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerSurName() {
        return consumerSurName;
    }

    public void setConsumerSurName(String consumerSurName) {
        this.consumerSurName = consumerSurName;
    }

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


}
