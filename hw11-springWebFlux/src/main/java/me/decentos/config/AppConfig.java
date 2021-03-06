package me.decentos.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import me.decentos.config.changelog.LibraryChangeLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig {

    private static final String LIBRARY = "library";

    @Bean
    @ConditionalOnProperty(prefix = "app.config", name = "dbType", havingValue = "mongo-docker")
    public Mongock mongock(final ApplicationContext ac, final MongoClient mongoClient) {
        log.info("invoked mongock(ApplicationContext, MongoClient)");
        return new SpringBootMongockBuilder(mongoClient, LIBRARY,
                LibraryChangeLog.class.getPackage().getName())
                .setApplicationContext(ac)
                .setLockQuickConfig()
                .build();
    }

}
