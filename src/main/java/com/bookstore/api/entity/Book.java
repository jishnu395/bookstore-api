package com.bookstore.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Book details")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ID of the book", example = "1")
    private Long id;

    public Book() {}

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public Double getPrice() { return price; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPrice(Double price) { this.price = price; }
    @Schema(description = "Book title", example = "Clean Code")
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @Schema(description = "Book author", example = "Robert C. Martin")
    @NotBlank(message = "Author name is required")
    private String author;
    @Schema(description = "ISBN number", example = "9780132350884")
    @NotBlank(message = "ISBN must be provided")
    private String isbn;
    @Schema(description = "Book price", example = "29.99")
    @NotNull(message = "Price cannot be Null")
    private Double price;



}
