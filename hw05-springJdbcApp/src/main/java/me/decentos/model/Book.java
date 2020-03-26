package me.decentos.model;

public class Book {

    private long id;
    private final String title;
    private final long authorId;
    private final long bookId;

    public Book(String title, long authorId, long bookId) {
        this.title = title;
        this.authorId = authorId;
        this.bookId = bookId;
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

    public long getBookId() {
        return bookId;
    }
}
