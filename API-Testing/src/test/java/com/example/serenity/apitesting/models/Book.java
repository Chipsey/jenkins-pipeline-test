package com.example.serenity.apitesting.models;

public class Book {
    private int id;
    private String title;
    private String author;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(int id, String title, int tag) {
        this.id = id;
        switch (tag) {
            case 1: {
                this.title = title;
                break;
            }
            case 2: {
                this.author = title;
                break;
            }
        }
    }

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
