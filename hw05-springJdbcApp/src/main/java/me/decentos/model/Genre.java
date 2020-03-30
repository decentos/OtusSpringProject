package me.decentos.model;

public class Genre {

    private long id;
    private final String genre;

    public Genre(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

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
