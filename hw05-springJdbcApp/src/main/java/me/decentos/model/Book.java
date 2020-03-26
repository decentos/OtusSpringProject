package me.decentos.model;

public class Book {

    private long id;
    private final String title;
    private final long authorId;
    private final long genreId;

    public Book(long id, String title, long authorId, long genreId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public Book(String title, long authorId, long genreId) {
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getAuthorId() {
        return authorId;
    }

    public long getGenreId() {
        return genreId;
    }
}
