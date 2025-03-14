package ru.kirius.book_service.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity for digital book
 */


@Table("digitalbooks")
public class DigitalBook implements BookInterface{

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("authorname")
    private String authorName;

    @Column("authorsurname")
    private String authorSurName;

    @Column("description")
    private String description;

    @Column("bookcoverurl")
    private String bookCoverUrl;

    @Column("pdfbookurl")
    private String pdfBookUrl;

    @Column("fb2bookurl")
    private String fb2BookUrl;

    @Column("price")
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurName() {
        return authorSurName;
    }

    public void setAuthorSurName(String authorSurName) {
        this.authorSurName = authorSurName;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPdfBookUrl() {
        return pdfBookUrl;
    }

    public void setPdfBookUrl(String pdfBookUrl) {
        this.pdfBookUrl = pdfBookUrl;
    }

    public String getFb2BookUrl() {
        return fb2BookUrl;
    }

    public void setFb2BookUrl(String fb2BookUrl) {
        this.fb2BookUrl = fb2BookUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
