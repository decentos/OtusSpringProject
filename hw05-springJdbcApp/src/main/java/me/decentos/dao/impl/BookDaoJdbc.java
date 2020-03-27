package me.decentos.dao.impl;

import me.decentos.dao.BookDao;
import me.decentos.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        jdbc.getJdbcOperations().update("insert into books (`title`, `author_id`, `genre_id`) values (?, ?, ?)",
                book.getTitle(),
                book.getAuthorId(),
                book.getGenreId()
        );
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject(
                "select * from books where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            return new Book(id, title, authorId, genreId);
        }
    }
}
