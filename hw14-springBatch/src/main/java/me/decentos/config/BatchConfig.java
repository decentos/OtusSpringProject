package me.decentos.config;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class BatchConfig {
    private final Logger logger = LoggerFactory.getLogger("Batch");

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job migrateLibraryJob(@Qualifier("migrateAuthor") Step migrateAuthor,
                                 @Qualifier("migrateGenre") Step migrateGenre,
                                 @Qualifier("migrateBook") Step migrateBooks) {
        return jobBuilderFactory.get("migrateLibraryJob")
                .incrementer(new RunIdIncrementer())
                .start(migrateAuthor)
                .next(migrateGenre)
                .next(migrateBooks)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job");
                    }
                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }


    @Bean
    Step migrateAuthor(ItemWriter<Author> writer, ItemReader<Author> reader, ItemProcessor<Author, Author> itemProcessor) {
        return stepBuilderFactory.get("migrateAuthors")
                .<Author, Author>chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    Step migrateGenre(ItemWriter<Genre> writer, ItemReader<Genre> reader, ItemProcessor<Genre, Genre> itemProcessor) {
        return stepBuilderFactory.get("migrateGenre")
                .<Genre, Genre>chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    Step migrateBook(ItemWriter<Book> writer, ItemReader<Book> reader, ItemProcessor<Book, Book> itemProcessor) {
        return stepBuilderFactory.get("migrateBook")
                .<Book, Book>chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }
}
