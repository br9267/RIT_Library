package com.example.rit_library;

public class bookDatabase {
    public String id;
    public String title;
    public String author;
    public Integer ISBN;
    public String genre;
    public String format;
    public boolean availability;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        if(availability == 1){
            this.availability = true;
        }
        else{
            this.availability = false;
        }
    }

    @Override
    public String toString() {
        return "bookDatabase{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", genre='" + genre + '\'' +
                ", format='" + format + '\'' +
                ", availability=" + availability +
                '}';
    }

    public bookDatabase(String id, String title, String author, Integer ISBN, String genre, String format, Integer availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.format = format;
        if(availability == 1){
            this.availability = true;
        }
        else{
            this.availability = false;
        }

    }
}
