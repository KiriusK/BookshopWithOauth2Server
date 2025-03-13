package ru.kirius.order_database_service.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ordernumber")
public class OrderNumber {

    @Id
    private Integer id;

    @Column("num")
    private int num;

    @Column("lastordernumber")
    private long lastOrderNumber;

    public OrderNumber(int num, long lastOrderNumber) {
        this.num = num;
        this.lastOrderNumber = lastOrderNumber;
    }

    public OrderNumber() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getLastOrderNumber() {
        return lastOrderNumber;
    }

    public void setLastOrderNumber(long lastOrderNumber) {
        this.lastOrderNumber = lastOrderNumber;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
