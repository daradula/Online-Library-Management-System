package com.library.book.dto;

import jakarta.validation.constraints.NotBlank;

public class BookRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    private String category;
    private String isbn;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}