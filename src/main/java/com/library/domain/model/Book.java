package com.library.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Boolean available; // emprestado ou nn

    public Book() {}

    public Book(UUID id, String title, String author, Boolean available){

        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }


}
