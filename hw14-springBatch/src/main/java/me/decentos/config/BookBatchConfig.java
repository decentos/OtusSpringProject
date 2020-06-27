package me.decentos.config;

import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class BookBatchConfig {

    @Bean
    public ItemReader<Book> bookItemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Book>()
                .name("bookItemReader")
                .dataSource(dataSource)
                .sql("select b.id, b.title, a.id as aid, a.first_name as firstName, a.last_name as lastName, " +
                        "g.id as gid, g.genre as genreName from books b" +
                        " inner join authors a on a.id=b.author_id inner join genres g on g.id=b.genre_id")
                .rowMapper(new BookBatchConfig.BookMapper())
                .build()
                ;
    }

    @Bean
    public ItemProcessor<Book, Book> bookItemProcessor() {
        return book -> book;
    }

    @Bean
    public ItemWriter<Book> bookItemWriter(MongoTemplate template) {
        return new MongoItemWriterBuilder<Book>()
                .template(template)
                .build();
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");

            String aid = resultSet.getString("aid");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Author author = new Author(aid, firstName, lastName);

            String gid = resultSet.getString("gid");
            String genreName = resultSet.getString("genreName");
            Genre genre = new Genre(gid, genreName);

            return new Book(id, title, author, genre);
        }
    }
}
