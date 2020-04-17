package me.decentos.config.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Slf4j
@ChangeLog
public class LibraryChangeLog {

    private static final String DATABASE = "library";
    private static final String AUTHORS = "authors";
    private static final String GENRES = "genres";
    private static final String BOOKS = "books";
    private static final String AUTHORS_JSON_RESOURCE = "json/authors.json";
    private static final String GENRES_JSON_RESOURCE = "json/genres.json";
    private static final String BOOKS_JSON_RESOURCE = "json/books.json";

    private static CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    @ChangeSet(author = "user", id = "dropDb", order = "001", runAlways = true)
    public void dropDb(final MongoTemplate template) {
        template.getDb().drop();
        log.info("Dropped database {}", DATABASE);
    }

    @ChangeSet(author = "user", id = "createGenres", order = "020")
    public void createGenres(final MongoTemplate template) {
        val collection = template.getDb().getCollection(GENRES);

        final List<String> jsonFileContent = getFileContents(GENRES_JSON_RESOURCE);
        final String jsonFromFile = readFileToString(jsonFileContent);
        final Genre[] genres = new Gson().fromJson(jsonFromFile, Genre[].class);

        for (Genre g : genres) {
            collection.insertOne(new Document()
                    .append("_id", new ObjectId(g.getId()))
                    .append("genreName", g.getGenre())
            );
        }
        log.info("Created collection {}", GENRES);
    }

    @ChangeSet(author = "user", id = "createAuthors", order = "010")
    public void createAuthors(final MongoTemplate template) {
        val collection = template.getDb().getCollection(AUTHORS);

        final List<String> jsonFileContent = getFileContents(AUTHORS_JSON_RESOURCE);
        final String jsonFromFile = readFileToString(jsonFileContent);
        final Author[] authors = new Gson().fromJson(jsonFromFile, Author[].class);

        for (Author a : authors) {
            collection.insertOne(new Document()
                    .append("_id", new ObjectId(a.getId()))
                    .append("firstName", a.getFirstName())
                    .append("lastName", a.getLastName())
            );
        }
        log.info("Created collection {}", AUTHORS);
    }

    @ChangeSet(author = "user", id = "createBooks", order = "030")
    public void createBooks(final MongoTemplate template) {
        val collection = template.getDb().withCodecRegistry(codecRegistry).getCollection(BOOKS);

        final List<String> jsonFileContent = getFileContents(BOOKS_JSON_RESOURCE);
        final String jsonFromFile = readFileToString(jsonFileContent);
        final Book[] books = new Gson().fromJson(jsonFromFile, Book[].class);

        for (Book b : books) {
            collection.insertOne(new Document()
                    .append("_id", new ObjectId(b.getId()))
                    .append("authors", b.getAuthor())
                    .append("comments", b.getComments())
                    .append("genres", b.getGenre())
                    .append("title", b.getTitle())
            );
        }
        log.info("Created collection {}", BOOKS);
    }

    public String readFileToString(final List<String> list) {
        String result;
        try (Stream<String> lines = list.stream()) {
            result = lines
                    .map(String::trim)
                    .collect(Collectors.joining());
        }
        return result;
    }

    private List<String> getFileContents(final String resource) {
        final List<String> list = new ArrayList<>();
        String str;
        try (final InputStream is = getClass().getClassLoader().getResourceAsStream(resource);
             final BufferedReader br = new BufferedReader(new InputStreamReader(requireNonNull(is)))) {
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            log.error("Exception in LibraryChangeLog class: {}", e);
        }
        return list;
    }

}
