package me.decentos.config;

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
public class GenreBatchConfig {

    @Bean
    public ItemReader<Genre> genreItemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Genre>()
                .name("genreItemReader")
                .dataSource(dataSource)
                .sql("select genres.id, genres.genre from genres")
                .rowMapper(new GenreBatchConfig.GenreMapper())
                .build();
    }

    @Bean
    public ItemProcessor<Genre, Genre> genreItemProcessor() {
        return genre -> genre;
    }

    @Bean
    public ItemWriter<Genre> genreItemWriter(MongoTemplate template) {
        return new MongoItemWriterBuilder<Genre>()
                .template(template)
                .build();
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            String id=resultSet.getString("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
