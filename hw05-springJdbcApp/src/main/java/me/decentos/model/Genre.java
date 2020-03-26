package me.decentos.model;

public class Genre {

    private long id;
    private final String genre;

    public Genre(String genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }
}
