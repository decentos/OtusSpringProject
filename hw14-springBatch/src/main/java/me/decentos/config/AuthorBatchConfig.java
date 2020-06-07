package me.decentos.config;

import me.decentos.model.Author;
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
public class AuthorBatchConfig {

    @Bean
    public ItemReader<Author> authorItemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Author>()
                .name("authorItemReader")
                .dataSource(dataSource)
                .sql("select * from authors")
                .rowMapper(new AuthorBatchConfig.AuthorMapper())
                .build();
    }

    @Bean
    public ItemProcessor<Author, Author> authorItemProcessor() {
        return author -> author;
    }

    @Bean
    public ItemWriter<Author> authorItemWriter(MongoTemplate template) {
        return new MongoItemWriterBuilder<Author>()
                .template(template)
                .build();
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
