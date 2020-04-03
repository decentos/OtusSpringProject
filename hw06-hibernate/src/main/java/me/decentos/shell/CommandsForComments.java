package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Book;
import me.decentos.model.Comment;
import me.decentos.repositories.BookRepository;
import me.decentos.repositories.CommentRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommandsForComments {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @ShellMethod(value = "Get count of comments", key = {"commentsCount"})
    public int getCount() {
        return commentRepository.count();
    }

    @ShellMethod(value = "Save comment", key = {"saveComment"})
    public String save(String commentary, long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Comment comment = new Comment(commentary, book);
        commentRepository.save(comment);
        return String.format("Вы добавили новый комментраий: %s для книги: %s", comment.getCommentary(), comment.getBook().getTitle());
    }

    @ShellMethod(value = "Find comment", key = {"findComment"})
    public String findById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        return String.format("Комментраий: \"%s\" для книги: %s", comment.getCommentary(), comment.getBook().getTitle());
    }

    @ShellMethod(value = "Find all comments", key = {"findAllComments"})
    public void findAll() {
        List<Comment> list = commentRepository.findAll();
        list.forEach(comment -> System.out.println(String.format("Комментраий: \"%s\" для книги: %s", comment.getCommentary(), comment.getBook().getTitle())));
    }

    @ShellMethod(value = "Find all comments by book id", key = {"findAllCommentsByBookId"})
    public void findAllByBookId(long bookId) {
        List<Comment> list = commentRepository.findAllByBookId(bookId);
        System.out.println("Все комментраии для книги: " + bookRepository.findById(bookId).orElseThrow().getTitle());
        list.forEach(comment -> System.out.println(comment.getCommentary()));
        if (list.size() == 0) System.out.println("Для данной книги еще не оставлен ни один комментарий");
    }

    @ShellMethod(value = "Update comment", key = {"updateComment"})
    public String updateCommentById(long id, String commentary, long bookId) {
        Comment oldComment = commentRepository.findById(id).orElseThrow();
        String oldCommentary = oldComment.getCommentary();

        Book book = bookRepository.findById(bookId).orElseThrow();
        commentRepository.updateCommentById(new Comment(id, commentary, book));

        Comment updateComment = commentRepository.findById(id).orElseThrow();
        String updateCommentary = updateComment.getCommentary();

        return String.format("Вы успешло изменили комментарий с \"%s\" на \"%s\" для книги %s", oldCommentary, updateCommentary, book.getTitle());
    }

    @ShellMethod(value = "Delete comment", key = {"deleteComment"})
    public String deleteById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        commentRepository.deleteById(id);
        return String.format("Вы удалили комментарий \"%s\" для книги %s", comment.getCommentary(), comment.getBook().getTitle());
    }
}
